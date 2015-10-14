/**
 * Edit Asset JS edit.js
 */

function datepick(value) {
	var $this = $('#' + value.id);
	if (!$this.hasClass('hasDatepicker')) {
		if (value.id == 'invoiceDate') {
			$this.datepicker({
				dateFormat : "dd-mm-yy",
				maxDate : 0,
				changeMonth : true,
				changeYear : true,
			});
		} else {
			$this.datepicker({
				dateFormat : "dd-mm-yy",
				changeMonth : true,
				changeYear : true,
			});
		}
	}
}

$(document).ready( function() {


			$("#editasset").submit(function() {
				$.ajax({
					url : $(this).attr("action"),
					context : document.body,
					type : 'post',
					data : $(this).serialize()
				}).done(function(res) {
					popupInfo(res, 'alert-info');

				}).fail(function(res) {

					popupInfo("Problem in editing Asset!", 'alert-error');
				});
				return false;

			});
		});
