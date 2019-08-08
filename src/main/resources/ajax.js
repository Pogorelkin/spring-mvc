$(document).ready(function () {
    $("#productTable").on("click", "#ajax", function() {
        var $call = $(this);
        var employeeId = $call.closest('tr').find("td:first").text();
        var durl = '/delete-employee/' + employeeId;
        $.ajax({
            type: 'GET',
            url: durl
        }).done(
            function (answer) {
                $call.closest('tr').remove();
            }
        )
    })
})