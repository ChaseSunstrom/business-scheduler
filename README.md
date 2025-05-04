# User Manual

This manual provides instructions on using the Business Scheduler JavaFX desktop application.

## Requirements

- Java 11 or higher installed
- Gradle (or use the Gradle wrapper)

## Installation & Launch

```bash
# Clone the repository
git clone https://github.com/ChaseSunstrom/business-scheduler.git
cd business-scheduler

# Build and run with Gradle
gradle build
gradle run
```

Alternatively, run the fat JAR after building:

```bash
java -jar build/libs/business-scheduler-1.0-SNAPSHOT.jar
```

## Application Overview

- **Resources Pane** (left): Manage Resources (ID, Name, Type).
- **Tasks Pane** (right): List and organize Tasks by ID, Urgency, Deadline, Assigned Resource, and Status.

## Adding a Resource

1. Click **Add Resource**.
2. Enter Resource **ID**, **Name**, and **Type**.
3. Click **Save** to add it to the table.

## Managing Tasks

- **Add Task**:
  1. Click **Add Task**.
  2. Fill in **Task ID**, **Name**, **Urgency** (1–5), **Deadline**, and select an **Assigned Resource**.
  3. Click **Save**.

- **Edit Task**:
  1. Right-click a task row and choose **Edit**.
  2. Update fields in the form and click **Save**.

- **Delete Task**:
  1. Right-click a task row and choose **Delete**.
  2. Confirm deletion in the prompt.

- **Set Status**:
  1. Click the **Status** cell in the Tasks table.
  2. Select **PENDING**, **FINISHED**, or **CANCELED** from the dropdown.

- **Clear Finished/Canceled**:
  - Use the **Clear Finished** or **Clear Canceled** buttons below the task form to bulk-remove tasks.

## Sorting & Prioritization

Tasks are automatically sorted using **MergeSort** by:
1. Urgency (descending)
2. Deadline (ascending)

## Persistence

All data is stored in memory during runtime. Data will reset when the application closes.

## Troubleshooting

- Verify JavaFX modules are properly set on the module path.
- Ensure `styles.css` and FXML files are present under `src/main/resources`.
- Consult the console output for stack traces and error details.

For more details, refer to the developer guide and JUnit test reports. 