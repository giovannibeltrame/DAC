$(function(){
	
	w2utils.locale('../../../../lib/bower_components/w2ui/locale/pt-br.json');
	
	var situacao = [ { id: 'A', text: 'Ativa' }, { id: 'I', text: 'Inativa' } ];
	
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
			{ field: 'nome', caption: 'Nome', size: '40%' },
			{ field: 'localizacao', caption: 'Localização', size: '40%' },
			{ field: 'situacao', caption: 'Situação', size: '10%', render: function(record) { return record.situacao == 'A' ? 'Ativa' : 'Inativa'; } }
		],
		searches: [
			{ field: 'id', caption: 'Cód.', type: 'int' },
			{ field: 'nome', caption: 'Nome', type: 'text' }
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
		header: 'Gerenciador de Departamentos',
		name: 'form',
		fields: [
			{ name: 'id', type: 'text', html: { caption: 'Cód.', attr: 'size="40" readonly' } },
			{ name: 'nome', type: 'text', required: true, html: { caption: 'Nome', attr: 'size="40" maxlength="255"' } },
			{ name: 'localizacao', type: 'text', required: true, html: { caption: 'Localização', attr: 'size="40" maxlength="255"' } },
			{ name: 'situacao', type: 'list', required: true, html: { caption: 'Situação' }, options: { items: situacao } }
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
				w2ui.form.record.situacao = w2ui.form.record.situacao.id;
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
			url: 'http://' + window.location.host + '/departamentos',
			cache: false,
						
			success: function(data) {
				w2ui.grid.add(data);
			},
			error: function(jqXHR, ajaxOptions, thrownError) {
    			w2popup.open({
					title: 'Erro',
					body: '<div class="w2ui-centered">Erro ao buscar os departamentos.<br/>' + thrownError + jqXHR.responseText + '</div>',
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
			url: 'http://' + window.location.host + '/departamentos',
			cache: false,
			
			success: function(data) {
				w2popup.open({
					title: 'Sucesso',
					body: '<div class="w2ui-centered">Departamento inserido com sucesso.</div>',
                    showMax: true
				});
				requestFindAll();
			},
			error: function(jqXHR, ajaxOptions, thrownError) {
    			w2popup.open({
					title: 'Erro',
					body: '<div class="w2ui-centered">Erro ao inserir o departamento.<br/>' + thrownError + jqXHR.responseText + '</div>',
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
			url: 'http://' + window.location.host + '/departamentos',
			cache: false,
			
			success: function(data) {
				w2popup.open({
					title: 'Sucesso',
					body: '<div class="w2ui-centered">Departamento editado com sucesso.</div>',
                    showMax: true
				});
				requestFindAll();
			},
			error: function(jqXHR, ajaxOptions, thrownError) {
    			w2popup.open({
					title: 'Erro',
					body: '<div class="w2ui-centered">Erro ao editar o departamento.<br/>' + thrownError + jqXHR.responseText + '</div>',
                    showMax: true
				});
    		}
		});
	}
	
	function requestDelete() {
		$.ajax({
    		type: 'DELETE',
    		url: 'http://' + window.location.host + '/departamentos/' + w2ui.form.recid,
    		cache: false,
    					
    		success: function(data) {
    			w2popup.open({
					title: 'Sucesso',
					body: '<div class="w2ui-centered">Departamento excluído com sucesso.</div>',
                    showMax: true
				});
    			requestFindAll();
    		},
    		error: function(jqXHR, ajaxOptions, thrownError) {
    			w2popup.open({
					title: 'Erro',
					body: '<div class="w2ui-centered">Erro ao excluir o departamento.<br/>' + thrownError + jqXHR.responseText + '</div>',
                    showMax: true
				});
    		}
    	});
	}
	
	$(".w2ui-form-box").css('width', '100%');
	$(".w2ui-field").css('display', 'inline-block');
	
});