$(document).ready(function() {
    $("#write_done").click(function () {
        alert("yes");
        $.confirm({
            title: '确认完成草拟合同',
            content: '确认完成后将不可更改 <br> 请仔细核对合同款项',
            confirmButton: '确认草拟完成',
            cancelButton: '取消',
            confirmButtonClass: 'btn-info',
            icon: 'fa fa-question-circle',
            animation: 'rotate',
            closeAnimation: 'right',
            opacity: 0.5,
            confirm: function () {
                $.alert({
                    title: '草拟合同成功!',
                    content: '您已成功草拟此份合同，请耐心等待对方同意',
                    confirmButton: '我知道了',
                    confirmButtonClass: 'btn-primary',
                    icon: 'fa fa-info',
                    animation: 'zoom',
                    confirm: function () {

                    }
                });
            }
        });
    });
})