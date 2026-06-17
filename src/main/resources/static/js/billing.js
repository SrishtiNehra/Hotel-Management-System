window.onload = function () {

    fetch("/api/billings", {
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    })
    .then(res => res.json())
    .then(data => {

        let container = document.getElementById("billingContainer");

        data.forEach(b => {
            container.innerHTML += `
                <div>
                    <p>Amount: ₹${b.amount}</p>
                    <p>Status: ${b.paymentStatus}</p>
                </div>
            `;
        });

    });
};