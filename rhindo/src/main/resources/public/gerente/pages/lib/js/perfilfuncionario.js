$(function(){
	
	var funcionario = [];
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
	
	var selectFunc = '<select name="funcionario" class="form-control"><option value="">Selecione o funcionário</option>';
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
				if (data.length > 0) {
					
				}
				
			},
			error: function(jqXHR, textStatus) {
				console.log('ERROR: ' + textStatus);
			}					
		});
	});
	
});