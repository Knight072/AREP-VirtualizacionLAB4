function loadGetMsg(event) {
    event.preventDefault();
    let msgVar = document.getElementById("mensaje").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
        displayMsgs(JSON.parse(this.responseText));
    }
    xhttp.open("GET", "/app/round-robin?message=" + msgVar);
    xhttp.send();
}

function displayMsgs(data) {
    console.log(data);
    const messageList = document.getElementById('messageList');
    messageList.innerHTML = '';
    data.forEach(message => {
        const newRow = document.createElement('tr');
        newRow.innerHTML = `
            <td>${message.content}</td>
            <td>${message.date}</td>
        `;
        messageList.appendChild(newRow);
    });
}
