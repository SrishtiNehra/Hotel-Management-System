console.log("bookings.js loaded");

// ================= AUTH HEADER =================
function authHeader() {
    return {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + localStorage.getItem("token")
    };
}

// ================= LOAD BOOKINGS =================
window.addEventListener("load", loadBookings);

function loadBookings() {

    fetch("/api/reservations", {
        headers: authHeader()
    })
    .then(res => {
        if (!res.ok) throw new Error("Failed to load bookings");
        return res.json();
    })
    .then(data => {

        let rows = "";

        data.forEach(r => {

            let actionButtons = "";

            // ================= BOOKED =================
            if (r.status === "BOOKED") {

                actionButtons = `
                    <button class="btn btn-success btn-sm"
                        onclick="goToPayment(${r.reservationId})">
                        Pay
                    </button>

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
                        onclick="cancelReservation(${r.reservationId})">
                        Cancel
                    </button>
                `;
            }

            // ================= CANCELLED =================
            else if (r.status === "CANCELLED") {

                actionButtons = `
                    <button class="btn btn-danger btn-sm"
                        onclick="deleteReservation(${r.reservationId})">
                        Delete
                    </button>
                `;
            }

            // ================= PAID =================
            else if (r.status === "PAID") {

                actionButtons = `<span class="badge bg-success">PAID</span>`;
            }

            // ================= OTHER STATUSES =================
            else {
                actionButtons = `
                    <span class="badge bg-secondary">${r.status}</span>
                `;
            }

            rows += `
                <tr>
                    <td>${r.reservationId}</td>
                    <td>${r.plannedCheckIn}</td>
                    <td>${r.plannedCheckOut}</td>
                    <td>${r.status}</td>
                    <td>${r.roomId}</td>
                    <td>${actionButtons}</td>
                </tr>
            `;
        });

        document.getElementById("bookingTable").innerHTML = rows;
    })
    .catch(err => console.error("Error:", err));
}

// ================= CANCEL RESERVATION =================
function cancelReservation(id) {

    if (!confirm("Cancel this booking?")) return;

    fetch(`/api/reservations/${id}/cancel`, {
        method: "PUT",
        headers: authHeader()
    })
    .then(res => {
        if (!res.ok) throw new Error("Cancel failed");
        alert("Reservation cancelled");
        loadBookings();
    })
    .catch(err => alert(err.message));
}

// ================= DELETE RESERVATION =================
function deleteReservation(id) {

    if (!confirm("Delete this booking permanently?")) return;

    fetch(`/api/reservations/${id}`, {
        method: "DELETE",
        headers: authHeader()
    })
    .then(res => {
        if (!res.ok) throw new Error("Delete failed");
        alert("Reservation deleted");
        loadBookings();
    })
    .catch(err => alert(err.message));
}

// ================= PAYMENT REDIRECT =================
function goToPayment(reservationId) {
    window.location.href = `/customer/payment?reservationId=${reservationId}`;
}

// ================= EDIT MODAL (placeholder if you use it) =================
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

// ================= UPDATE RESERVATION =================
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
    })
    .catch(err => alert(err.message));
}