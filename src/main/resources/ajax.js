$(document).ready(function () {
    $("#productTable").on("click", "#ajax", function() {
        var $call = $(this);
        var employeeId = $call.closest('tr').find("td:first").text();
        var durl = '/employee/' + employeeId;
        $.ajax({
            type: 'DELETE',
            url: durl
        }).done(
            function (answer) {
                $call.closest('tr').remove();
            }
        )
    })
})