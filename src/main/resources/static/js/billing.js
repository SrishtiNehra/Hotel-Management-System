console.log("bills.js loaded");

window.onload = function () {

    const token = localStorage.getItem("token");

    if (!token) {
        alert("Login required");
        window.location.href = "/login";
        return;
    }

    fetch("/api/billings/customer", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        }
    })
    .then(res => {
        if (!res.ok) {
            throw new Error("Failed to load bills");
        }
        return res.json();
    })
    .then(data => {

        const container = document.getElementById("billContainer");

        if (!container) {
            console.error("billContainer not found in HTML");
            return;
        }

        container.innerHTML = "";

        if (!data || data.length === 0) {
            container.innerHTML = `
                <div class="alert alert-info">
                    No bills found
                </div>`;
            return;
        }

        data.forEach(bill => {

            container.innerHTML += `
                <div class="card p-3 shadow-sm mb-3">

                    <h5>Bill ID: ${bill.billingId}</h5>
                    <p>Amount: ₹${bill.amount}</p>
                    <p>Status: ${bill.paymentStatus}</p>
                    <p>Date: ${bill.paymentDate}</p>

                    <button class="btn btn-primary"
                        onclick="downloadPDF(${bill.billingId})">
                        Download PDF
                    </button>

                </div>
            `;
        });
    })
    .catch(err => {
        console.error("Billing error:", err);
        document.getElementById("billContainer").innerHTML =
            "<p class='text-danger'>Failed to load bills</p>";
    });
};

function downloadPDF(id) {
    window.open("/api/billings/" + id + "/pdf", "_blank");
}