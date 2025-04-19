$(document).ready(function() {
    // Delete: Begin
    $("tbody tr .delete").on("click", function() {
        let checkbox = $(this).closest("tr").find("input[type='checkbox']");
        checkbox.prop("checked", true);
    });

    $('#deleteEmployeeModal form').on('submit', function(event) {
        Delete();
    });

    function Delete() {
        let selectedIds = [];
        $('.custom-checkbox input[type="checkbox"]:checked').each(function() {
            selectedIds.push($(this).val());
        });
        let hiddenInput = '<input type="hidden" name="selectedIds" value="' + selectedIds.join(',') + '">';
        $('#deleteEmployeeModal form').append(hiddenInput);
    }
    // Delete: End
});