exports.parse = function parse(data) {
    var lines = data.toString('ascii').split("\n");
    var cut, name, host;
    for (var i = 0, len = lines.length; i < len; i++) {
        cut = lines[i].split(':');
        name = cut[0];
        if (name === 'Host') {
            if (cut[1] === undefined) {
                return 'Host header not found';
            }
            host = cut[1].trim().toLowerCase();
            return host;
        }
    }
    return null;
};