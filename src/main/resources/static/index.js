let shortHash = new URLSearchParams(window.location.search).get('shortUrl')

if (shortHash) {
    let shortUrl = location.protocol + '//' + location.host + '/r/' + shortHash

    document.getElementById('alert').innerHTML =
        '<div class="alert alert-success" role="alert">' +
        'Successfully created ShortR URL: ' +
        '<input type="url" class="form-control" value="' + shortUrl + '">' +
        '</div>'
}

let error = new URLSearchParams(window.location.search).get('error')

if (error) {
    document.getElementById('alert').innerHTML =
        '<div class="alert alert-danger" role="alert">' +
        'Unknown server error, please try again later.' +
        '</div>'
}
