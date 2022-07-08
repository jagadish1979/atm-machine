$('.btn-view-sms').click(function(){
    //clone dialog and remove ids to ensure uniqueness
    var $modal = $('#smsModal').clone().removeAttr('id');

    //apply custom values where needed
    var $btn = $(this);
    var mobile = $btn.attr('data-mobile');
    var stime = $btn.attr('data-time');
    var sms = $btn.attr('data-sms');

    $modal.find('[data-value="mobile"]').text(mobile);
    $modal.find('[data-value="time"]').text(stime);
    $modal.find('[data-value="sms"]').text(sms);
    //show dialog
    $modal.modal();
});