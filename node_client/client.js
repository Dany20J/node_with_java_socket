const net = require("net");

function reconnect() {
    var socket = new net.Socket();
    socket.setTimeout(10000000)
    socket.setKeepAlive(1, 12324)

    socket.connect(4060, "localhost", function () {
        console.log("Client: Connected to server");
    });

    socket.on("data", function (data) {
        console.log("Response from server: %s", data);

    })
    socket.on("close", function (hasError) {
        reconnect()
    })

    return (data) => {
        socket.write(data)
        socket.end()
    }
}
let writeFun = reconnect()
setTimeout(() => {
    writeFun("HELLO I AM NODE JS")
}, 2000)



