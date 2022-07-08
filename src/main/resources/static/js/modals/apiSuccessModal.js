$('.btn-view-apisuccess').click(function(){
    //clone dialog and remove ids to ensure uniqueness
    var $modal = $('#apiSuccessModal').clone().removeAttr('id');

    //apply custom values where needed
    var $btn = $(this);
    var serviceId = $btn.attr('data-serviceId');
    var requestTime = $btn.attr('data-requestTime');
    var responseTime = $btn.attr('data-responseTime');
    var request = $btn.attr('data-request');
    var response = $btn.attr('data-response');
    var userId = $btn.attr('data-userId');
    
    $modal.find('[data-value="serviceId"]').text(serviceId);
    $modal.find('[data-value="requestTime"]').text(requestTime);
    $modal.find('[data-value="responseTime"]').text(responseTime);
    $modal.find('[data-value="request"]').text(request);
    $modal.find('[data-value="response"]').text(response);
    $modal.find('[data-value="userId"]').text(userId);
    //show dialog
    $modal.modal();
});