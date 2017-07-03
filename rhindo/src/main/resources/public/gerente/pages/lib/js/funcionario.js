$(function(){
	
	w2utils.locale('../../../../lib/bower_components/w2ui/locale/pt-br.json');
	
	var cidade = [];
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: 'http://' + window.location.host + '/cidades',
		cache: false,
		async: false,
					
		success: function(data) {
			cidade = $.extend(true, [], data);
			for (i in cidade) {
				cidade[i].text = cidade[i].nome;
			}
		},
		error: function(jqXHR, textStatus) {
			console.log('ERROR: ' + textStatus);
		}					
	});
			
	$('#grid').w2grid({
		name: 'grid',
		header: 'Funcionários',
		recid: 'id',
		show: {
			toolbar: true,
			footer: true,
			toolbarAdd: false,
			toolbarDelete: true
		},
		sortData: [{ field: 'recid', direction: 'ASC' }],
		columns: [
			{ field: 'id', caption: 'Cód.', size: '10%' },
			{ field: 'nome', caption: 'Nome', size: '10%' },
			{ field: 'celular', caption: 'Celular', size: '10%' },
			{ field: 'email', caption: 'Email', size: '10%' },
			{ field: 'cpf', caption: 'CPF', size: '10%' },
			{ field: 'rg', caption: 'RG', size: '10%' },
			{ field: 'cidade', caption: 'Cidade', size: '10%', render: function(record) { return record.cidade && record.cidade.nome; } },
			{ field: 'rua', caption: 'Rua', size: '10%' },
			{ field: 'numero', caption: 'Número', size: '10%' },
			{ field: 'complemento', caption: 'Complemento', size: '10%' },
			{ field: 'bairro', caption: 'Bairro', size: '10%' },
			{ field: 'cep', caption: 'CEP', size: '10%' }
		],
		searches: [
			{ field: 'id', caption: 'Cód.', type: 'int' },
			{ field: 'nome', caption: 'Nome', type: 'text' },
			{ field: 'cpf', caption: 'CPF', type: 'text' }
		],
		onClick: function(event) {
			var grid = this;
			var form = w2ui.form;
			event.onComplete = function () {
				var sel = grid.getSelection();
                if (sel.length == 1) {
                	form.recid  = sel[0];
                    form.record = $.extend(true, {}, grid.get(sel[0]));
                    form.refresh();
                } else {
                	form.clear();
                }
			}
		},
		onReload: function(event) {
			requestFindAll();
		},
		onDelete: function(event) {
	        if (event.force) {
	        	var record = $.extend(true, {}, w2ui.form.record);
	        	requestDelete(record);
	        }
	    }    
	});
			
	$('#form').w2form({
		header: 'Gerenciador de Funcionários',
		name: 'form',
		fields: [
			{ name: 'id', type: 'text', html: { caption: 'Cód.', attr: 'size="40" readonly' } },
			{ name: 'nome', type: 'text', required: true, html: { caption: 'Nome', attr: 'size="40" maxlength="255"' } },
			{ name: 'celular', type: 'text', required: true, html: { caption: 'Celular', attr: 'size="40" maxlength="16"' } },
			{ name: 'email', type: 'text', required: true, html: { caption: 'Email', attr: 'size="40" maxlength="64"' } },
			{ name: 'senha', type: 'password', required: true, html: { caption: 'Senha', attr: 'size="40" maxlength="32"' } },
			{ name: 'confirmarsenha', type: 'password', required: true, html: { caption: 'Confirme a senha', attr: 'size="40" maxlength="32"' } },
			{ name: 'cpf', type: 'text', required: true, html: { caption: 'CPF', attr: 'size="40" maxlength="16"' } },
			{ name: 'rg', type: 'text', required: true, html: { caption: 'RG', attr: 'size="40" maxlength="16"' } },
			{ name: 'cidade', type: 'list', required: true, options: { items: cidade }, html: { caption: 'Cidade', attr: 'size="40"' } },
			{ name: 'rua', type: 'text', required: true, html: { caption: 'Rua', attr: 'size="40" maxlength="64"' } },
			{ name: 'numero', type: 'int', required: true, options: { autoFormat: false }, html: { caption: 'Número', attr: 'size="40" maxlength="8"' } },
			{ name: 'complemento', type: 'text', required: true, html: { caption: 'Complemento', attr: 'size="40" maxlength="32"' } },
			{ name: 'bairro', type: 'text', required: true, html: { caption: 'Bairro', attr: 'size="40" maxlength="64"' } },
			{ name: 'cep', type: 'text', required: true, html: { caption: 'CEP', attr: 'size="40" maxlength="16"' } }
		],
		actions: {
			Reset: function () {
				this.clear();
			},
			Save: function () {
				var errors = this.validate();
				if (errors.length > 0) {
					return;
				}
				var record = $.extend(true, {}, w2ui.form.record);
				var msg = validateForm(record);
				if (msg) {
					w2popup.open({
						title: 'Erro',
						body: '<div class="w2ui-centered">' + msg + '</div>',
	                    showMax: true
					});
					return;
				}
				delete record.recid;
				delete record.confirmarsenha;
				delete record.cidade.text;
				if (record.id === '') {
					requestInsert(record);
				} else {
					requestUpdate(record);
				}
			}
		}
	});
	
	function validateForm(record) {
		if (!(record.senha.trim() !== '' && record.senha.trim() === record.confirmarsenha.trim())) {
			return 'As senhas informadas são diferentes.';
		}
		if (!w2utils.isEmail(record.email)) {
			return 'O email informado não é válido.';
		}
		if (!TestaCPF(record.cpf)) {
			return 'O CPF informado não é válido.';
		}
		if (record.cep.replace(/[^\d]+/g,'').length !== 8) {
			return 'O CEP informado não é válido.';
		}
		return;
	}
	
	function TestaCPF(strCPF) {
		var Soma = 0, Resto = 0;
	    strCPF = strCPF.replace(/[^\d]+/g,''); 
	    if (strCPF.length != 11 || 
	    strCPF === "00000000000" || 
	    strCPF === "11111111111" || 
	    strCPF === "22222222222" || 
	    strCPF === "33333333333" || 
	    strCPF === "44444444444" || 
	    strCPF === "55555555555" || 
	    strCPF === "66666666666" || 
	    strCPF === "77777777777" || 
	    strCPF === "88888888888" || 
	    strCPF === "99999999999")
	    	return false;  

	    for (i=1; i<=9; i++)
	    	Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (11 - i);
	    Resto = (Soma * 10) % 11;
	    if ((Resto === 10) || (Resto === 11))
	    	Resto = 0;
	    if (Resto != parseInt(strCPF.substring(9, 10)) )
	    	return false;
	    Soma = 0;
	    for (i = 1; i <= 10; i++)
	    	Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (12 - i);
	    Resto = (Soma * 10) % 11;

	    if ((Resto === 10) || (Resto === 11))
	    	Resto = 0;
	    if (Resto != parseInt(strCPF.substring(10, 11) ) ) return false;
	    return true;
	}
	
	requestFindAll();
	function requestFindAll() {
		w2ui.grid.clear();
		w2ui.form.clear();
		$.ajax({
			type: 'GET',
			dataType: 'json',
			url: 'http://' + window.location.host + '/funcionarios',
			cache: false,
						
			success: function(data) {
				w2ui.grid.add(data);
			},
			error: function(jqXHR, ajaxOptions, thrownError) {
    			w2popup.open({
					title: 'Erro',
					body: '<div class="w2ui-centered">Erro ao buscar os funcionários.<br/>' + thrownError + jqXHR.responseText + '</div>',
                    showMax: true
				});
    		}	
		});
	}
	
	function requestInsert(record) {
		$.ajax({
			type: 'POST',
			data: JSON.stringify(record),
			contentType: 'application/json',
			dataType: 'json',
			url: 'http://' + window.location.host + '/funcionarios',
			cache: false,
			
			success: function(data) {
				w2popup.open({
					title: 'Sucesso',
					body: '<div class="w2ui-centered">Funcionário inserido com sucesso.</div>',
                    showMax: true
				});
				requestFindAll();
			},
			error: function(jqXHR, ajaxOptions, thrownError) {
    			w2popup.open({
					title: 'Erro',
					body: '<div class="w2ui-centered">Erro ao inserir o funcionário.<br/>' + thrownError + jqXHR.responseText + '</div>',
                    showMax: true
				});
    		}
		});
	}
	
	function requestUpdate(record) {
		$.ajax({
			type: 'PUT',
			data: JSON.stringify(record),
			contentType: 'application/json',
			dataType: 'json',
			url: 'http://' + window.location.host + '/funcionarios',
			cache: false,
			
			success: function(data) {
				w2popup.open({
					title: 'Sucesso',
					body: '<div class="w2ui-centered">Funcionário editado com sucesso.</div>',
                    showMax: true
				});
				requestFindAll();
			},
			error: function(jqXHR, ajaxOptions, thrownError) {
    			w2popup.open({
					title: 'Erro',
					body: '<div class="w2ui-centered">Erro ao editar o funcionário.<br/>' + thrownError + jqXHR.responseText + '</div>',
                    showMax: true
				});
    		}
		});
	}
	
	function requestDelete(record) {
		$.ajax({
    		type: 'DELETE',
    		url: 'http://' + window.location.host + '/funcionarios/' + record.id,
    		cache: false,
    					
    		success: function(data) {
    			w2popup.open({
					title: 'Sucesso',
					body: '<div class="w2ui-centered">Funcionário excluído com sucesso.</div>',
                    showMax: true
				});
    			requestFindAll();
    		},
    		error: function(jqXHR, ajaxOptions, thrownError) {
    			w2popup.open({
					title: 'Erro',
					body: '<div class="w2ui-centered">Erro ao excluir o funcionário.<br/>' + thrownError + jqXHR.responseText + '</div>',
                    showMax: true
				});
    		}
    	});
	}
					
	$('cpf').mask('999.999.999-99', {autoclear: false});
	$('#rg').mask('99.999.999-9', {autoclear: false});
	$('#cep').mask('99.999-999', {autoclear: false});
    $('#celular').mask('(99)99999-9999', {autoclear: false});

	$('.w2ui-form-box').css('width', '100%');
	$('.w2ui-field').css('display', 'inline-block');
});