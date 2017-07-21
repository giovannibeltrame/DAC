$(function(){
	
	$('#labelDepto').hide();
	$('#labelCargo').hide();
	$('#labelSave').hide();
	
	var funcionario = [];
	var departamento = [];
	var cargo = [];
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: 'http://' + window.location.host + '/funcionarios',
		cache: false,
		async: false,
					
		success: function(data) {
			funcionario = $.extend(true, [], data);
		},
		error: function(jqXHR, textStatus) {
			console.log('ERROR: ' + textStatus);
		}					
	});
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: 'http://' + window.location.host + '/departamentos',
		cache: false,
		async: false,
					
		success: function(data) {
			departamento = $.extend(true, [], data);
		},
		error: function(jqXHR, textStatus) {
			console.log('ERROR: ' + textStatus);
		}					
	});
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url: 'http://' + window.location.host + '/cargos',
		cache: false,
		async: false,
					
		success: function(data) {
			cargo = $.extend(true, [], data);
		},
		error: function(jqXHR, textStatus) {
			console.log('ERROR: ' + textStatus);
		}					
	});
	
	var selectFunc = '<select id="funcionario" name="funcionario" class="form-control col-xs-3"><option value="">Selecione</option>';
	for (i in funcionario) {
		selectFunc += '<option value="' + funcionario[i].id + '">' + funcionario[i].nome + '</option>';
	}
	selectFunc += '</select>';
	$('#selectFunc').html(selectFunc);
	
	$('#ok').click(function() {
		var idfuncionario = $('#selectFunc option:selected').val();
		if (idfuncionario == '') {
			return;
		}
		$.ajax({
			type: 'GET',
			dataType: 'json',
			url: 'http://' + window.location.host + '/cargo-atribuido-funcionario/' + idfuncionario,
			cache: false,
			async: false,
						
			success: function(data) {
				var perfil = $.extend(true, {}, data);
				loadProfile(perfil);
			},
			error: function(jqXHR, textStatus) {
				console.log('ERROR: ' + textStatus);
			}					
		});
	});
	
	function loadProfile(perfil) {
		var selectDepto = '<select name="departamento" class="form-control col-xs-3"><option value="">Selecione</option>';
		for (i in departamento) {
			selectDepto += '<option value="' + departamento[i].id + '"';
			if (perfil.departamentoAlocaFuncionario && perfil.departamentoAlocaFuncionario.departamento.id == departamento[i].id) {
				selectDepto += ' selected';
			}
			selectDepto += '>' + departamento[i].nome + '</option>';
		}
		selectDepto += '</select>';
		$('#selectDepto').html(selectDepto);
		$('#labelDepto').show();
		
		var selectCargo = '<select name="cargo" class="form-control col-xs-3"><option value="">Selecione</option>';
		for (i in cargo) {
			selectCargo += '<option value="' + cargo[i].id + '"';
			if (perfil.cargo && perfil.cargo.id == cargo[i].id) {
				selectCargo += ' selected';
			}
			selectCargo += '>' + cargo[i].nome + '</option>';
		}
		selectCargo += '</select>';
		$('#selectCargo').html(selectCargo);
		$('#labelCargo').show();
		
		$('#labelSave').show();
	}
	
	$('#save').click(function() {
		var idfuncionario = $('#selectFunc option:selected').val();
		var iddepartamento = $('#selectDepto option:selected').val();
		var idcargo = $('#selectCargo option:selected').val();
		if (idfuncionario == '' || iddepartamento == '' || idcargo == '') {
			alert('Todos os campos são obrigatórios.');
			return;
		}
		var perfil = {};
		perfil.id = {};
		perfil.departamentoAlocaFuncionario = {};
		perfil.departamentoAlocaFuncionario.id = {};
		perfil.cargo = {};
		perfil.id.idFuncionario = idfuncionario;
		perfil.departamentoAlocaFuncionario.id.idFuncionario = idfuncionario;
		perfil.id.idDepartamento = iddepartamento;
		perfil.departamentoAlocaFuncionario.id.idDepartamento = iddepartamento;
		perfil.id.idCargo = idcargo;
		for (i in funcionario) {
			if (funcionario[i].id == idfuncionario) {
				perfil.departamentoAlocaFuncionario.funcionario = funcionario[i];
			}
		}
		for (i in departamento) {
			if (departamento[i].id == iddepartamento) {
				perfil.departamentoAlocaFuncionario.departamento = departamento[i];
			}
		}
		for (i in cargo) {
			if (cargo[i].id == idcargo) {
				perfil.cargo = cargo[i];
			}
		}
		$.ajax({
			type: 'POST',
			data: JSON.stringify(perfil),
			contentType: 'application/json',
			dataType: 'json',
			url: 'http://' + window.location.host + '/cargo-atribuido-funcionario',
			cache: false,
			
			success: function(data) {
				$('#msg').html('<div class="alert alert-success">Perfil do funcionário salvo com sucesso.</div>');
			},
			error: function(jqXHR, ajaxOptions, thrownError) {
				$('#msg').html('<div class="alert alert-danger">Ocorreu um erro. ' + thrownError + '</div>');
				console.log('ERROR: ' + thrownError);
    		}
		});
	});
	
});