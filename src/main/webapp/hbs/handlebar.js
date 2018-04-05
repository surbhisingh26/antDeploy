var template = $('#handlebars-demo').html();

var context = { "name" : "Ritesh Kumar", "occupation" : "developer" , hobbies:[{label:"Singing", id: 1},{label:"dancing", id:"2"}]};

//Compile the template data into a function
var templateScript = Handlebars.compile(template);

var html = templateScript(context);
//html = 'My name is Ritesh Kumar . I am a developer.'

$(document.body).append(html);
