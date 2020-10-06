let shortHash = new URLSearchParams(window.location.search).get('shortUrl')

if (shortHash) {
    let shortUrl = location.protocol + '//' + location.host + '/r/' + shortHash

    document.getElementById('success').innerHTML =
        '<div class="alert alert-success" role="alert">' +
        'Successfully created ShortR URL: ' +
        '<input type="url" class="form-control" value="' + shortUrl + '">'
        '</div>'

}
