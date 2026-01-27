const API_URL = "http://localhost:8080/students";

function addStudent() {
  const name = document.getElementById("name").value;
  const age = document.getElementById("age").value;
  
  // Validation
  if (!name || !age) {
    alert("Please fill in both name and age");
    return;
  }
  
  fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ name, age })
  })
  .then(res => res.json())
  .then(() => {
    // Clear inputs after successful add
    document.getElementById("name").value = "";
    document.getElementById("age").value = "";
    loadStudents();
  })
  .catch(err => console.error("Error adding student:", err));
}

function loadStudents() {
  fetch(API_URL)
    .then(res => res.json())
    .then(data => {
      const tbody = document.querySelector("#students tbody");
      
      if (data.length === 0) {
        tbody.innerHTML = "";
        return;
      }
      
      tbody.innerHTML = data.map(s => `
        <tr>
          <td>${s.ID}</td>
          <td>${s.name}</td>
          <td>${s.age}</td>
          <td>
            <div class="action-buttons">
              <button class="update-btn" onclick="updateStudent(${s.ID}, '${s.name}', ${s.age})">Update</button>
              <button class="delete-btn" onclick="deleteStudent(${s.ID})">Delete</button>
            </div>
          </td>
        </tr>
      `).join("");
    })
    .catch(err => console.error("Error loading students:", err));
}

function updateStudent(id, currentName, currentAge) {
  const newName = prompt("Enter new name:", currentName);
  const newAge = prompt("Enter new age:", currentAge);
  
  if (newName === null || newAge === null) {
    return; // User cancelled
  }
  
  if (!newName || !newAge) {
    alert("Name and age cannot be empty");
    return;
  }
  
  fetch(`${API_URL}/${id}`, {
    method: "PATCH",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ name: newName, age: parseInt(newAge) })
  })
  .then(res => res.json())
  .then(() => {
    console.log("Updated student with ID:", id);
    loadStudents();
  })
  .catch(err => console.error("Error updating student:", err));
}

function deleteStudent(id) {
  if (!confirm("Are you sure you want to delete this student?")) {
    return;
  }
  
  fetch(`${API_URL}/${id}`, { method: "DELETE" })
    .then(() => {
      console.log("Deleted student with ID:", id);
      loadStudents();
    })
    .catch(err => console.error("Error deleting student:", err));
}

// Load students when page loads
loadStudents();