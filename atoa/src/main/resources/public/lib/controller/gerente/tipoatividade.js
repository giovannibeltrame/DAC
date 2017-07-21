$(function(){
	
	w2utils.locale('../../../../lib/bower_components/w2ui/locale/pt-br.json');
	
	var situacao = [ { id: 'A', text: 'Ativa' }, { id: 'I', text: 'Inativa' } ];
	var departamento = [];
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: 'http://' + window.location.host + '/departamentos',
		cache: false,
		async: false,
					
		success: function(data) {
			departamento = $.extend(true, [], data);
			for (i in departamento) {
				departamento[i].text = departamento[i].nome;
			}
		},
		error: function(jqXHR, textStatus) {
			console.log('ERROR: ' + textStatus);
		}					
	});
	
	$('#grid').w2grid({
		name: 'grid',
		header: 'Tipos de Atividade',
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
			{ field: 'situacao', caption: 'Situação', size: '10%', render: function(record) { return record.situacao == 'A' ? 'Ativa' : 'Inativa'; } },
			{ field: 'departamento', caption: 'Departamento', size: '10%', render: function(record) { return record.departamento && record.departamento.nome; } }
		],
		searches: [
			{ field: 'id', caption: 'Cód.', type: 'int' },
			{ field: 'nome', caption: 'Nome', type: 'text' },
			{ field: 'departamento', caption: 'Departamento', type: 'text' }
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
		header: 'Gerenciador de Tipos de Atividade',
		name: 'form',
		fields: [
			{ name: 'id', type: 'text', html: { caption: 'Cód.', attr: 'size="40" readonly' } },
			{ name: 'nome', type: 'text', required: true, html: { caption: 'Nome', attr: 'size="40" maxlength="255"' } },
			{ name: 'situacao', type: 'list', required: true, options: { items: situacao }, html: { caption: 'Situação', attr: 'size="40"' } },
			{ name: 'departamento', type: 'list', required: true, options: { items: departamento }, html: { caption: 'Departamento', attr: 'size="40"' } }
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
				delete record.departamento.text;
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
			url: 'http://' + window.location.host + '/tiposatividades',
			cache: false,
						
			success: function(data) {
				w2ui.grid.add(data);
			},
			error: function(jqXHR, ajaxOptions, thrownError) {
    			w2popup.open({
					title: 'Erro',
					body: '<div class="w2ui-centered">Erro ao buscar os tipos de atividade.<br/>' + thrownError + jqXHR.responseText + '</div>',
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
			url: 'http://' + window.location.host + '/tiposatividades',
			cache: false,
			
			success: function(data) {
				w2popup.open({
					title: 'Sucesso',
					body: '<div class="w2ui-centered">Tipo de atividade inserido com sucesso.</div>',
                    showMax: true
				});
				requestFindAll();
			},
			error: function(jqXHR, ajaxOptions, thrownError) {
    			w2popup.open({
					title: 'Erro',
					body: '<div class="w2ui-centered">Erro ao inserir o tipo de atividade.<br/>' + thrownError + jqXHR.responseText + '</div>',
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
			url: 'http://' + window.location.host + '/tiposatividades',
			cache: false,
			
			success: function(data) {
				w2popup.open({
					title: 'Sucesso',
					body: '<div class="w2ui-centered">Tipo de atividade editado com sucesso.</div>',
                    showMax: true
				});
				requestFindAll();
			},
			error: function(jqXHR, ajaxOptions, thrownError) {
    			w2popup.open({
					title: 'Erro',
					body: '<div class="w2ui-centered">Erro ao editar o tipo de atividade.<br/>' + thrownError + jqXHR.responseText + '</div>',
                    showMax: true
				});
    		}
		});
	}
	
	function requestDelete(record) {
		$.ajax({
    		type: 'DELETE',
    		url: 'http://' + window.location.host + '/tiposatividades/' + record.id,
    		cache: false,
    					
    		success: function(data) {
    			w2popup.open({
					title: 'Sucesso',
					body: '<div class="w2ui-centered">Tipo de atividade excluído com sucesso.</div>',
                    showMax: true
				});
    			requestFindAll();
    		},
    		error: function(jqXHR, ajaxOptions, thrownError) {
    			w2popup.open({
					title: 'Erro',
					body: '<div class="w2ui-centered">Erro ao excluir o tipo de atividade.<br/>' + thrownError + jqXHR.responseText + '</div>',
                    showMax: true
				});
    		}
    	});
	}
					
	$(".w2ui-form-box").css('width', '100%');
	$(".w2ui-field").css('display', 'inline-block');
});