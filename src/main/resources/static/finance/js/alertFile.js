function alertFile(str) {

    $.alert({
        title: str,
        content: '',
        confirmButton: '我知道了',
        confirmButtonClass: 'btn-primary',
        icon: 'fa fa-info',
        animation: 'zoom',
        confirm: function () {

        }
    });

}

function infoFile(str) {

    $.dialog({
        title: str,
        content: '',
        icon: 'fa fa-info',
        animation: 'zoom',
        confirm: function () {

        }
    });

}