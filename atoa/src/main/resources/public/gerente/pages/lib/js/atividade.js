$(function(){
	
	w2utils.locale('../../../../lib/bower_components/w2ui/locale/pt-br.json');
	
	var funcionario = [];
	var dataCadastroParsed;
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
		{ field: 'dataHoraCadastro', caption: 'Cadastro', size: '10%', render:  function(record) {return showDate(new Date(record.dataHoraCadastro));} },
		{ field: 'dataHoraInicio', caption: 'Inicio', size: '10%' , render:  function(record) {return showDate(new Date(record.dataHoraInicio));} },
		{ field: 'dataHoraFim', caption: 'Fim', size: '10%' , render:  function(record) {return showDate(new Date(record.dataHoraFim));} },
		{ field: 'dataHoraPrevisaoInicio', caption: 'Previsão Início', size: '10%' , render:  function(record) {return showDate(new Date(record.dataHoraPrevisaoInicio));} },
		{ field: 'dataHoraPrevisaoFim', caption: 'Previsão Fim', size: '10%' , render:  function(record) {return showDate(new Date(record.dataHoraPrevisaoFim));} },
		{ field: 'descricao', caption: 'Descrição', size: '10%'},
		{ field: 'funcionario', caption: 'Funcionário', size: '10%' , render: function(record) { return record.funcionario && record.funcionario.nome; } },
		{ field: 'tipoAtividade', caption: 'Tipo da Atividade', size: '10%' , render: function(record) { return record.tipoAtividade && record.tipoAtividade.nome; } }
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
					var selected = grid.get(sel[0]);
					dataCadastroParsed = selected.dataHoraCadastro;
					selected.dataHoraCadastro = showParsedDate(selected.dataHoraCadastro);
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
		{ name: 'dataHoraCadastro', type: 'text', required: false, html: { caption: 'Data Cadastro', attr: 'size="40" readonly' } },
		{ name: 'dataHoraInicio', type: 'date', required: true, html: { caption: 'Data Início', attr: 'size="40" maxlength="16"' } },
		{ name: 'horaInicio', type: 'time', required: true, html: { caption: 'Hora Início', attr: 'size="40" maxlength="16"' } },
		{ name: 'dataHoraFim', type: 'date', required: true, html: { caption: 'Data Fim', attr: 'size="40" maxlength="64"' } },
		{ name: 'horaFim', type: 'time', required: true, html: { caption: 'Hora Fim', attr: 'size="40" maxlength="64"' } },
		{ name: 'dataHoraPrevisaoInicio', type: 'date', required: true, html: { caption: 'Data Previsão Início', attr: 'size="40" maxlength="32"' } },
		{ name: 'horaPrevisaoInicio', type: 'time', required: true, html: { caption: 'Hora Previsão Início', attr: 'size="40" maxlength="32"' } },
		{ name: 'dataHoraPrevisaoFim', type: 'date', required: true, html: { caption: 'Data Previsão Fim', attr: 'size="40" maxlength="32"' } },
		{ name: 'horaPrevisaoFim', type: 'time', required: true, html: { caption: 'Hora Previsão Fim', attr: 'size="40" maxlength="32"' } },
		{ name: 'descricao', type: 'text', required: true, html: { caption: 'Descrição', attr: 'size="40" maxlength="16"' } },
		{ name: 'funcionario', type: 'list', required: true, options: {items: funcionario}, html: { caption: 'Funcionário', attr: 'size="40"' } },
		{ name: 'tipoAtividade', type: 'list', required: true, options: { items: tipoAtividade }, html: { caption: 'Tipo Atividade', attr: 'size="40"' } }
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
				delete record.tipoAtividade.text
				var msg = validateForm(record);
				if (msg) {
					w2popup.open({
						title: 'Erro',
						body: '<div class="w2ui-centered">' + msg + '</div>',
						showMax: true
					});
					return;
				}
				if (record.id === '') {
					requestInsert(record);
				} else {
					requestUpdate(record);
				}
			}
		}
	});
	
	function validateForm(record) {

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

		var cadastro = new Date();

		record.dataHoraCadastro = dateFormat(cadastro);      
		record.dataHoraInicio = dateSplit(dataHoraInicio.value, horaInicio.value);
		record.dataHoraFim = dateSplit(dataHoraFim.value, horaFim.value);
		record.dataHoraPrevisaoInicio = dateSplit(dataHoraPrevisaoInicio.value,horaPrevisaoInicio.value);
		record.dataHoraPrevisaoFim = dateSplit(dataHoraPrevisaoFim.value,horaPrevisaoFim.value);

		delete record.horaFim;
		delete record.horaInicio;
		delete record.horaPrevisaoInicio;
		delete record.horaPrevisaoFim;

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
					body: '<div class="w2ui-centered">Atividade inserida com sucesso.</div>',
					showMax: true
				});
				requestFindAll();
			},
			error: function(jqXHR, ajaxOptions, thrownError) {
				w2popup.open({
					title: 'Erro',
					body: '<div class="w2ui-centered">Erro ao inserir a atividade.<br/>' + thrownError + jqXHR.responseText + '</div>',
					showMax: true
				});
			}
		});
	}
	
	function requestUpdate(record) {

		record.dataHoraCadastro = dataCadastroParsed;      
		record.dataHoraInicio = dateSplit(dataHoraInicio.value, horaInicio.value);
		record.dataHoraFim = dateSplit(dataHoraFim.value, horaFim.value);
		record.dataHoraPrevisaoInicio = dateSplit(dataHoraPrevisaoInicio.value,horaPrevisaoInicio.value);
		record.dataHoraPrevisaoFim = dateSplit(dataHoraPrevisaoFim.value,horaPrevisaoFim.value);

		delete record.horaFim;
		delete record.horaInicio;
		delete record.horaPrevisaoInicio;
		delete record.horaPrevisaoFim;

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
					body: '<div class="w2ui-centered">Atividade editada com sucesso.</div>',
					showMax: true
				});
				requestFindAll();
			},
			error: function(jqXHR, ajaxOptions, thrownError) {
				w2popup.open({
					title: 'Erro',
					body: '<div class="w2ui-centered">Erro ao editar a atividade.<br/>' + thrownError + jqXHR.responseText + '</div>',
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
					body: '<div class="w2ui-centered">Atividade excluída com sucesso.</div>',
					showMax: true
				});
				requestFindAll();
			},
			error: function(jqXHR, ajaxOptions, thrownError) {
				w2popup.open({
					title: 'Erro',
					body: '<div class="w2ui-centered">Erro ao excluir a atividade.<br/>' + thrownError + jqXHR.responseText + '</div>',
					showMax: true
				});
			}
		});
	}


	function dateFormat(d) {
		return (d.toISOString());
	}

	function showDate(d){
		var dformat = [d.getDate(),
		d.getMonth()+1,
		d.getFullYear()].join('/')+' '+
		[d.getHours(),
		d.getMinutes(),
		d.getSeconds()].join(':');
		return dformat;
	}

	function dateSplit(d, h) {
		var split = d.split('/');
		var date = new Date(split[2], split[1] - 1, split[0]); //Y M D 
		date = Date.parse(date);
		h = Date.parse('01/01/1970 ' + h) - 72*1000*100;
		date += h; //trata as horas e a data separadamente
		date = new Date(date); // cria uma nova data CORRETAMENTE
		return dateFormat(date);	
	}

	function showParsedDate(d){
		return showDate(new Date(d));

	}

	$('.w2ui-form-box').css('width', '100%');
	$('.w2ui-field').css('display', 'inline-block');
});