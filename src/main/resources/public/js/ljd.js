/* ========================================== 
scrollTop() >= 300
Should be equal the the height of the header
========================================== */
$(document).ready(function () {
	// the "href" attribute of the modal trigger must specify the modal ID that wants to be triggered
	$('.modal').modal({
		dismissible: true
	});
	Materialize.updateTextFields();
});

$(window).scroll(function () {
	var nav = $('nav')
	if ($(window).scrollTop() >= 300) {
		// we have scrolled down the page

		nav.addClass('fixed-header');
		nav.removeClass('translucent');
		nav.addClass('opaque');

		$('#site-title').removeClass('invisible');

	}
	else {
		// we are at the top of the page
		nav.removeClass('fixed-header');
		nav.addClass('translucent');
		nav.removeClass('opaque');


		$('#site-title').addClass('invisible');

	}
});



// generic function to open a modal dialog. Specify the controller path which will supply the view, and the HTML divs to update
function openModal(sparkPath, dataDiv, containerDiv) {
	$.ajax({
		url: sparkPath,
		success: function (data) {
			$(dataDiv).html(data);
			$(containerDiv).modal('open')
		}
	});
}

// Security and authentication
function authenticate() {
	validate("/auth/validateLogin", "login_form", "login_modal", "/")
}

// Call the validation route given in validatorPath to validate the form in formName.
// If validation fails, update containerDiv through AJAX.
// If it succeeds, redirect to the given route
function validate(validatorPath, formName, containerDiv, redirectPath) {
	var serializedData = $(formName).serialize();
	console.log(formName + " posted with " + serializedData);
	// first, post to validator
	$.ajax({
		url: validatorPath,
		method: 'post',
		data: serializedData,
		success: function (response, statusText, xhr) {
			// if valid, move on to next action
			if (!response) {
				window.location.href = redirectPath
			} else {
				// if in error, form should be re-rendered
				$('#' + containerDiv).html(response);
				Materialize.updateTextFields();
			}
		}
	});
}