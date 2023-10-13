$(document).ready(() => {
  if (sessionStorage.getItem('darkMode') === "true") {
    $('body').addClass('dark-mode');
    $('header nav')
    .addClass('navbar-dark')
    .removeClass('navbar-light');
    $('main, footer')
    .addClass('accent-light')
    .removeClass('accent-dark');
    $('.modal-body pre').addClass('text-light');
    $('#dark-mode-checkbox').prop('checked', true);
  }
})
$('#dark-mode-checkbox').on('click', function () {
  if ($(this).is(':checked')) {
    $('body').addClass('dark-mode');
    $('header nav')
    .addClass('navbar-dark')
    .removeClass('navbar-light');
    $('main, footer')
    .addClass('accent-light')
    .removeClass('accent-dark');
    $('.modal-body pre').addClass('text-light');
    sessionStorage.setItem('darkMode', "true");
  } else {
    $('body').removeClass('dark-mode');
    $('header nav')
    .addClass('navbar-light')
    .removeClass('navbar-dark');
    $('main, footer')
    .addClass('accent-dark')
    .removeClass('accent-light');
    $('.modal-body pre').removeClass('text-light');
    sessionStorage.setItem('darkMode', "false");
  }
});

$('.connectedSortable').sortable({
  placeholder: 'sort-highlight',
  connectWith: '.connectedSortable',
  handle: '.card-header, .nav-tabs',
  forcePlaceholderSize: true,
  zIndex: 999999
})
$('.connectedSortable .card-header').css('cursor', 'move')

// jQuery UI sortable for the todo list
$('.todo-list').sortable({
  placeholder: 'sort-highlight',
  handle: '.handle',
  forcePlaceholderSize: true,
  zIndex: 999999
})
