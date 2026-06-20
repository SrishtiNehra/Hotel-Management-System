console.log("bookings.js loaded");

function authHeader() {
    return {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + localStorage.getItem("token")
    };
}

window.onload = function() {
    loadBookings();
};

function loadBookings() {

    fetch("/api/reservations", {
        headers: authHeader()
    })
        .then(res => res.json())
        .then(data => {

            let rows = "";

            data.forEach(r => {

                rows += `
                <tr>
                    <td>${r.reservationId}</td>
                    <td>${r.plannedCheckIn}</td>
                    <td>${r.plannedCheckOut}</td>
                    <td>${r.status}</td>
                    <td>${r.roomId}</td>

					<td>
					    ${r.status === "BOOKED" ? `
					        <button class="btn btn-success btn-sm"
					            onclick="goToPayment(${r.reservationId})">
					            Pay
					        </button>
					    ` : ""}

					    <button class="btn btn-warning btn-sm"
					        onclick="openEdit(
					            ${r.reservationId},
					            '${r.plannedCheckIn}',
					            '${r.plannedCheckOut}',
					            '${r.status}'
					        )">
					        Edit
					    </button>

					    <button class="btn btn-danger btn-sm"
					        onclick="deleteReservation(${r.reservationId})">
					        Cancel
					    </button>

					    ${r.status === "PAID" ? `
					        <span class="badge bg-success">PAID</span>
					    ` : ""}
					</td>
                </tr>
            `;
            });

            document.getElementById("bookingTable").innerHTML = rows;
        });
}

function openEdit(id, checkIn, checkOut, status) {

    document.getElementById("editModal").style.display = "block";

    document.getElementById("editId").value = id;
    document.getElementById("editCheckIn").value = checkIn;
    document.getElementById("editCheckOut").value = checkOut;
    document.getElementById("editStatus").value = status;
}

function closeModal() {
    document.getElementById("editModal").style.display = "none";
}

function updateReservation() {

    const id = document.getElementById("editId").value;

    const reservation = {
        plannedCheckIn: document.getElementById("editCheckIn").value,
        plannedCheckOut: document.getElementById("editCheckOut").value,
        status: document.getElementById("editStatus").value
    };

    fetch(`/api/reservations/${id}`, {
        method: "PUT",
        headers: authHeader(),
        body: JSON.stringify(reservation)
    })
        .then(res => {
            if (!res.ok) throw new Error("Update failed");

            alert("Updated successfully");

            closeModal();
            loadBookings();
        });
}

function deleteReservation(id) {

    if (!confirm("Cancel this reservation?")) return;

    fetch(`/api/reservations/${id}/cancel`, {
        method: "PUT",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("token")
        }
    })
        .then(res => {
            if (!res.ok) throw new Error("Cancel failed");

            alert("Reservation Cancelled");
            loadBookings();
        })
        .catch(err => alert(err.message));
}

function goToPayment(reservationId) {

    window.location.href =
        `/customer/payment?reservationId=${reservationId}`;
}