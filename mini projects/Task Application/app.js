// Select the DOM elements
const taskInput = document.getElementById("task-input");
const dueDateInput = document.getElementById("due-date");
const prioritySelect = document.getElementById("priority");
const addButton = document.getElementById("add-task-btn");
const taskList = document.getElementById("task-list");

// Add a new task
function addTask() {
    const taskText = taskInput.value.trim();
    const dueDate = dueDateInput.value;
    const priority = prioritySelect.value;

    if (taskText !== "") {
        const li = document.createElement("li");
        li.textContent = taskText;
        li.classList.add('${priority}-priority');

        // Add due date
        if (dueDate) {
            const dueText = document.createElement("span");
            dueText.textContent = 'Due: ${dueDate'};
            dueText.classList.add("due-date");
            li.appendChild(dueText);
        }

        // Create the complete button
        const completeButton = document.createElement("button");
        completeButton.textContent = "Complete";
        completeButton.onclick = function () {
            li.classList.toggle("completed");
        };

        // Create the remove button
        const removeButton = document.createElement("button");
        removeButton.textContent = "Remove";
        removeButton.classList.add("remove");
        removeButton.onclick = function () {
            taskList.removeChild(li);
        };

        // Append buttons to the task item
        li.appendChild(completeButton);
        li.appendChild(removeButton);

        // Append the task item to the task list
        taskList.appendChild(li);

        // Clear the inputs after adding the task
        taskInput.value = "";
        dueDateInput.value = "";
        prioritySelect.value = "low";
    }


// Event listener for Add Task button
addButton.addEventListener("click", addTask);

// Allow pressing Enter to add task
taskInput.addEventListener("keydown", function (event) {
    if (event.key === "Enter") {
        addTask();
    }
});