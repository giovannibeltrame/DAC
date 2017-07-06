$(function(){
	
	w2utils.locale('../../../../lib/bower_components/w2ui/locale/pt-br.json');
	
	var situacao = [ { id: 'A', text: 'Ativa' }, { id: 'I', text: 'Inativa' } ];
	var gerente = [ { id: 'S', text: 'Sim' }, { id: 'N', text: 'Não' } ];
	
	$('#grid').w2grid({
		name: 'grid',
		header: 'Departamentos',
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
			{ field: 'percentualImposto', caption: '% Imposto', size: '10%', render: function(record) { return record.percentualImposto.toString().replace('.',',') + '%'; } },
			{ field: 'quantidadeMinimaHorasMes', caption: 'Qtde. Mínima Horas Mês', size: '10%' },
			{ field: 'salario', caption: 'Salário', size: '10%', render: 'currency' },
			{ field: 'gerente', caption: 'Gerente', size: '10%', render: function(record) { return record.gerente == 'S' ? 'Sim' : 'Não'; } },
			{ field: 'situacao', caption: 'Situação', size: '10%', render: function(record) { return record.situacao == 'A' ? 'Ativa' : 'Inativa'; } }
		],
		searches: [
			{ field: 'id', caption: 'Cód.', type: 'int' },
			{ field: 'nome', caption: 'Nome', type: 'text' },
			{ field: 'gerente', caption: 'Gerente', type: 'text' }
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
		header: 'Gerenciador de Cargos',
		name: 'form',
		fields: [
			{ name: 'id', type: 'text', html: { caption: 'Cód.', attr: 'size="40" readonly' } },
			{ name: 'nome', type: 'text', required: true, html: { caption: 'Nome', attr: 'size="40" maxlength="255"' } },
			{ name: 'percentualImposto', type: 'percent', required: true, options: { precision: 0 }, html: { caption: '% Imposto', attr: 'size="40" maxlength="32"' } },
			{ name: 'quantidadeMinimaHorasMes', type: 'int', required: true, options: { autoFormat: false }, html: { caption: 'Qtde. Mínima Horas Mês', attr: 'size="40" maxlength="8"' } },
			{ name: 'salario', type: 'currency', required: true, html: { caption: 'Salário', attr: 'size="40" maxlength="32"' } },
			{ name: 'gerente', type: 'list', required: true, options: { items: gerente }, html: { caption: 'Gerente', attr: 'size="40"' } },
			{ name: 'situacao', type: 'list', required: true, options: { items: situacao }, html: { caption: 'Situação', attr: 'size="40"' } }
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
				delete record.recid;
				record.gerente = record.gerente.id;
				record.situacao = record.situacao.id;
				if (record.id === '') {
					requestInsert(record);
				} else {
					requestUpdate(record);
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
			url: 'http://' + window.location.host + '/cargos',
			cache: false,
						
			success: function(data) {
				w2ui.grid.add(data);
			},
			error: function(jqXHR, ajaxOptions, thrownError) {
    			w2popup.open({
					title: 'Erro',
					body: '<div class="w2ui-centered">Erro ao buscar os cargos.<br/>' + thrownError + jqXHR.responseText + '</div>',
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
			url: 'http://' + window.location.host + '/cargos',
			cache: false,
			
			success: function(data) {
				w2popup.open({
					title: 'Sucesso',
					body: '<div class="w2ui-centered">Cargo inserido com sucesso.</div>',
                    showMax: true
				});
				requestFindAll();
			},
			error: function(jqXHR, ajaxOptions, thrownError) {
    			w2popup.open({
					title: 'Erro',
					body: '<div class="w2ui-centered">Erro ao inserir o cargo.<br/>' + thrownError + jqXHR.responseText + '</div>',
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
			url: 'http://' + window.location.host + '/cargos',
			cache: false,
			
			success: function(data) {
				w2popup.open({
					title: 'Sucesso',
					body: '<div class="w2ui-centered">Cargo editado com sucesso.</div>',
                    showMax: true
				});
				requestFindAll();
			},
			error: function(jqXHR, ajaxOptions, thrownError) {
    			w2popup.open({
					title: 'Erro',
					body: '<div class="w2ui-centered">Erro ao editar o cargo.<br/>' + thrownError + jqXHR.responseText + '</div>',
                    showMax: true
				});
    		}
		});
	}
	
	function requestDelete(record) {
		$.ajax({
    		type: 'DELETE',
    		url: 'http://' + window.location.host + '/cargos/' + record.id,
    		cache: false,
    					
    		success: function(data) {
    			w2popup.open({
					title: 'Sucesso',
					body: '<div class="w2ui-centered">Cargo excluído com sucesso.</div>',
                    showMax: true
				});
    			requestFindAll();
    		},
    		error: function(jqXHR, ajaxOptions, thrownError) {
    			w2popup.open({
					title: 'Erro',
					body: '<div class="w2ui-centered">Erro ao excluir o cargo.<br/>' + thrownError + jqXHR.responseText + '</div>',
                    showMax: true
				});
    		}
    	});
	}
	
	$('.w2ui-field').css('display', 'inline-block');
	$('.w2ui-form-box').css('width', '100%');	
});