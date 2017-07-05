$(function(){
	
	w2utils.locale('../../../../lib/bower_components/w2ui/locale/pt-br.json');
	
	var funcionario = [];
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: 'http://' + window.location.host + '/funcionarios',
		cache: false,
		async: false,
					
		success: function(data) {
			funcionario = $.extend(true, [], data);
			for (i in funcionario) {
				funcionario[i].text = funcionario[i].nome;
			}
		},
		error: function(jqXHR, textStatus) {
			console.log('ERROR: ' + textStatus);
		}					
	});
	
	var tipoAtividade = [];
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: 'http://' + window.location.host + '/tiposatividades',
		cache: false,
		async: false,
					
		success: function(data) {
			tipoAtividade = $.extend(true, [], data);
			for (i in tipoAtividade) {
				tipoAtividade[i].text = tipoAtividade[i].nome;
			}
		},
		error: function(jqXHR, textStatus) {
			console.log('ERROR: ' + textStatus);
		}					
	});

			
	$('#grid').w2grid({
		name: 'grid',
		header: 'Atividades',
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
			{ field: 'dataHoraCadastro', caption: 'Cadastro', size: '10%' },
			{ field: 'dataHoraInicio', caption: 'Inicio', size: '10%' },
			{ field: 'dataHoraFim', caption: 'Fim', size: '10%' },
			{ field: 'dataHoraPrevisaoInicio', caption: 'Previsão Início', size: '10%' },
			{ field: 'dataHoraPrevisaoFim', caption: 'Previsão Fim', size: '10%' },
			{ field: 'descricao', caption: 'Descrição', size: '10%',
			{ field: 'funcionario', caption: 'Funcionário', size: '10%' }, render: function(record) { return record.funcionario && record.funcionario.nome; } },
			{ field: 'tipoAtividade', caption: 'Tipo da Atividade', size: '10%' }, render: function(record) { return record.tipoAtividade && record.tipoAtividade.nome; } },
		],
		searches: [
			{ field: 'id', caption: 'Cód.', type: 'int' },
			{ field: 'descricao', caption: 'Descrição', type: 'text' },
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
		header: 'Gerenciador de Atividades',
		name: 'form',
		fields: [
			{ name: 'id', type: 'text', html: { caption: 'Cód.', attr: 'size="40" readonly' } },
			{ name: 'dataHoraCadastro', type: 'date', required: true, html: { caption: 'Data Hora Cadastro', attr: 'size="40" maxlength="255"' } },
			{ name: 'dataHoraInicio', type: 'date', required: true, html: { caption: 'Data Hora Início', attr: 'size="40" maxlength="16"' } },
			{ name: 'dataHoraFim', type: 'text', required: true, html: { caption: 'Data Hora FIm', attr: 'size="40" maxlength="64"' } },
			{ name: 'dataHoraPrevisaoInicio', type: 'text', required: true, html: { caption: 'Senha', attr: 'size="40" maxlength="32"' } },
			{ name: 'dataHoraPrevisaoFim', type: 'text', required: true, html: { caption: 'Confirme a senha', attr: 'size="40" maxlength="32"' } },
			{ name: 'descricao', type: 'text', required: true, html: { caption: 'CPF', attr: 'size="40" maxlength="16"' } },
			{ name: 'funcionario', type: 'text', required: true, html: { caption: 'RG', attr: 'size="40" maxlength="16"' } },
			{ name: 'tipoAtividade', type: 'list', required: true, options: { items: tipoAtividade }, html: { caption: 'Tipo Atividade', attr: 'size="40"' } },
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
				//delete record.recid;
				// delete record.confirmarsenha;
				// delete record.cidade.text;
				if (record.id === '') {
					requestInsert(record);
				} else {
					requestUpdate(record);
				}
			}
		}
	});
	
	function validateForm(record) {
		/*if (!(record.senha.trim() !== '' && record.senha.trim() === record.confirmarsenha.trim())) {
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
		}*/
		return;
	}
	
	
	
	requestFindAll();
	function requestFindAll() {
		w2ui.grid.clear();
		w2ui.form.clear();
		$.ajax({
			type: 'GET',
			dataType: 'json',
			url: 'http://' + window.location.host + '/atividades',
			cache: false,
						
			success: function(data) {
				w2ui.grid.add(data);
			},
			error: function(jqXHR, ajaxOptions, thrownError) {
    			w2popup.open({
					title: 'Erro',
					body: '<div class="w2ui-centered">Erro ao buscar atividades.<br/>' + thrownError + jqXHR.responseText + '</div>',
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
			url: 'http://' + window.location.host + '/atividades',
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
			url: 'http://' + window.location.host + '/atividades',
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
    		url: 'http://' + window.location.host + '/atividades/' + record.id,
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
					
	//$('#cpf').mask('999.999.999-99', {autoclear: false});
	//$('#rg').mask('99.999.999-9', {autoclear: false});
	//$('#cep').mask('99.999-999', {autoclear: false});
    //$('#celular').mask('(99)99999-9999', {autoclear: false});

	$('.w2ui-form-box').css('width', '100%');
	$('.w2ui-field').css('display', 'inline-block');
});