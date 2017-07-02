$(function(){
			
	w2utils.locale('../../../../lib/bower_components/w2ui/locale/pt-br.json');
			
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
//			{ field: 'senha', caption: 'Senha', size: '10%' },
			{ field: 'cpf', caption: 'CPF', size: '10%' },
			{ field: 'rg', caption: 'RG', size: '10%' },
//			{ field: 'cidade', caption: 'Cidade', size: '10%', render: function(record) { return record.cidade.nome; } },
			{ field: 'rua', caption: 'Rua', size: '10%' },
			{ field: 'numero', caption: 'Número', size: '10%' },
			{ field: 'complemento', caption: 'Complemento', size: '10%' },
			{ field: 'bairro', caption: 'Bairro', size: '10%' },
			{ field: 'cep', caption: 'CEP', size: '10%' }
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
	        	requestDelete();
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
			{ name: 'cpf', type: 'text', required: true, html: { caption: 'CPF', attr: 'size="40" maxlength="16"' } },
			{ name: 'rg', type: 'text', required: true, html: { caption: 'RG', attr: 'size="40" maxlength="16"' } },
//			{ name: 'cidade', type: 'list', required: true, html: { caption: 'Cidade' } },
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
				delete w2ui.form.record.recid;
				if (w2ui.form.record.id == '') {
					requestInsert();
				} else {
					requestUpdate();
				}
			}
		}
	});
	
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
	
	function requestInsert() {
		$.ajax({
			type: 'POST',
			data: JSON.stringify(w2ui.form.record),
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
	
	function requestUpdate() {
		$.ajax({
			type: 'PUT',
			data: JSON.stringify(w2ui.form.record),
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
	
	function requestDelete() {
		$.ajax({
    		type: 'DELETE',
    		url: 'http://' + window.location.host + '/funcionarios/' + w2ui.form.recid,
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
			
//	var cidade = [];
//	var request = $.ajax({
//		type: 'GET',
//		dataType: 'json',
//		url: 'http://' + window.location.host + '/cidades',
//		cache: false,
//					
//		success: function(data) {
//			console.log('DATA: ' + data);
//			cidade = data;
//			for (i in cidade) {
//				cidade[i].text = cidade[i].nome;
//			}
//			console.log(cidade);
//			$('input[name=cidade]').w2field('list', { items: cidade });
//		},
//		error: function(jqXHR, textStatus) {
//			console.log('ERROR: ' + textStatus);
//		}					
//	});

	$(".w2ui-form-box").css('width', '100%');
	$(".w2ui-field").css('display', 'inline-block');
});