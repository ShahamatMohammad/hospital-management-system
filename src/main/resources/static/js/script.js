document.getElementById("createHospitalForm").addEventListener("submit", function (event) {
    event.preventDefault();
    createHospital();
});

function createHospital() {
    const name = document.getElementById("name").value;
    const address = document.getElementById("address").value;
    const phone = document.getElementById("phone").value;
    const email = document.getElementById("email").value;
    const location = document.getElementById("location").value;
    const capacity = document.getElementById("capacity").value;

    fetch("/hospitals", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ name, address, phone, email, location, capacity }),
    })
        .then(response => response.json())
        .then(data => {
            alert("Hospital created successfully!");
            console.log(data);
        })
        .catch(error => {
            console.error("Error:", error);
        });
}

function getAllHospitals() {
    fetch("/hospitals")
        .then(response => response.json())
        .then(data => {
            const hospitalsList = document.getElementById("hospitalsList");
            hospitalsList.innerHTML = ""; // Clear the previous results
            data.forEach(hospital => {
                const div = document.createElement("div");
                div.textContent = `ID: ${hospital.id}, Name: ${hospital.name}, Location: ${hospital.location}`;
                hospitalsList.appendChild(div);
            });
        })
        .catch(error => {
            console.error("Error fetching hospitals:", error);
        });
}

function getHospitalById() {
    const id = document.getElementById("hospitalId").value;
    fetch(`/hospitals/${id}`)
        .then(response => response.json())
        .then(data => {
            const hospitalDetails = document.getElementById("hospitalDetails");
            hospitalDetails.innerHTML = `ID: ${data.id}, Name: ${data.name}, Location: ${data.location}`;
        })
        .catch(error => {
            console.error("Error fetching hospital:", error);
        });
}

function getHospitalsByName() {
    const name = document.getElementById("hospitalName").value;
    fetch(`/hospitals/name?name=${name}`)
        .then(response => response.json())
        .then(data => {
            const hospitalsByNameList = document.getElementById("hospitalsByNameList");
            hospitalsByNameList.innerHTML = ""; // Clear previous results
            data.forEach(hospital => {
                const div = document.createElement("div");
                div.textContent = `ID: ${hospital.id}, Name: ${hospital.name}, Location: ${hospital.location}`;
                hospitalsByNameList.appendChild(div);
            });
        })
        .catch(error => {
            console.error("Error fetching hospitals by name:", error);
        });
}

function getHospitalsByLocation() {
    const location = document.getElementById("hospitalLocation").value;
    console.log("Location input:", location); // Check input value

    fetch(`/hospitals/location?location=${location}`)
        .then(response => {
            console.log("Response status:", response.status); // Check response status
            return response.json();
        })
        .then(data => {
            console.log("Response data:", data); // Check received data
            const hospitalsByLocationList = document.getElementById("hospitalsByLocationList");
            hospitalsByLocationList.innerHTML = ""; // Clear previous results
            data.forEach(hospital => {
                const div = document.createElement("div");
                div.textContent = `ID: ${hospital.id}, Name: ${hospital.name}, Location: ${hospital.location}`;
                hospitalsByLocationList.appendChild(div);
            });
        })
        .catch(error => {
            console.error("Error fetching hospitals by location:", error);
        });
}


function getHospitalsByCapacity() {
    const capacity = document.getElementById("hospitalCapacity").value;
    fetch(`/hospitals/capacity?capacity=${capacity}`)
        .then(response => response.json())
        .then(data => {
            const hospitalsByCapacityList = document.getElementById("hospitalsByCapacityList");
            hospitalsByCapacityList.innerHTML = ""; // Clear previous results
            data.forEach(hospital => {
                const div = document.createElement("div");
                div.textContent = `ID: ${hospital.id}, Name: ${hospital.name}, Capacity: ${hospital.capacity}`;
                hospitalsByCapacityList.appendChild(div);
            });
        })
        .catch(error => {
            console.error("Error fetching hospitals by capacity:", error);
        });
}
function deleteHospital() {
    const hospitalId = document.getElementById("deleteHospitalId").value;

    if (hospitalId) {
        fetch(`/hospitals/${hospitalId}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    alert("Hospital deleted successfully!");
                } else {
                    alert("Failed to delete hospital. Please check the ID and try again.");
                }
            })
            .catch(error => {
                console.error("Error deleting hospital:", error);
                alert("An error occurred while deleting the hospital.");
            });
    } else {
        alert("Please enter a hospital ID.");
    }
}
function addPatientToHospital() {
    const hospitalId = document.getElementById("hospitalIdForPatient").value;
    const patientId = document.getElementById("patientId").value;

    if (hospitalId && patientId) {
        fetch(`/hospitals/${hospitalId}/patients/${patientId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ patientId: patientId })
        })
            .then(response => {
                if (response.ok) {
                    alert("Patient added to hospital successfully!");
                } else {
                    alert("Failed to add patient to hospital. Please check the IDs and try again.");
                }
            })
            .catch(error => {
                console.error("Error adding patient to hospital:", error);
                alert("An error occurred while adding the patient to the hospital.");
            });
    } else {
        alert("Please enter both hospital and patient IDs.");
    }
}
function addEmployeeToHospital() {
    const hospitalId = document.getElementById("hospitalIdForEmployee").value;
    const employeeId = document.getElementById("employeeId").value;

    if (hospitalId && employeeId) {
        fetch(`/hospitals/${hospitalId}/employees/${employeeId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ employeeId: employeeId })
        })
            .then(response => {
                if (response.ok) {
                    alert("Employee added to hospital successfully!");
                } else {
                    alert("Failed to add employee to hospital. Please check the IDs and try again.");
                }
            })
            .catch(error => {
                console.error("Error adding employee to hospital:", error);
                alert("An error occurred while adding the employee to the hospital.");
            });
    } else {
        alert("Please enter both hospital and employee IDs.");
    }
}

function addSecretaryToHospital() {
    const hospitalId = document.getElementById("hospitalIdForSecretary").value;
    const secretaryId = document.getElementById("secretaryId").value;

    if (hospitalId && secretaryId) {
        fetch(`/hospitals/${hospitalId}/secretaries/${secretaryId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ secretaryId: secretaryId })
        })
            .then(response => {
                if (response.ok) {
                    alert("Secretary added to hospital successfully!");
                } else {
                    alert("Failed to add secretary to hospital. Please check the IDs and try again.");
                }
            })
            .catch(error => {
                console.error("Error adding secretary to hospital:", error);
                alert("An error occurred while adding the secretary to the hospital.");
            });
    } else {
        alert("Please enter both hospital and secretary IDs.");
    }
}








