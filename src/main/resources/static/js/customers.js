window.onload = function () {

    fetch("/api/customers", {
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    })
    .then(res => res.json())
    .then(data => {

        let table = document.getElementById("customerTable");

        data.forEach(c => {
            table.innerHTML += `
                <tr>
                    <td>${c.customerId}</td>
                    <td>${c.fullName}</td>
                    <td>${c.email}</td>
                    <td>${c.status}</td>
                </tr>
            `;
        });

    });
};