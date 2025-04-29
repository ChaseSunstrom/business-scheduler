# Business Scheduler Application Proposal and Report

## Introduction
Business Scheduler is a standalone JavaFX desktop application designed to simplify resource management and task scheduling for small and medium-sized organizations. By combining custom data structures (hash table, binary search tree) and efficient algorithms (merge sort) with a modern, responsive UI, the app delivers fast in-memory operations and an intuitive user experience.

## Application Architecture & Features

1. **Data Models**
   - **Resource**: Represents personnel, equipment, or rooms. Stored in a **HashTable** for O(1) insert, lookup, and remove.
   - **Task**: Encapsulates ID, name, urgency (1–5), deadline, assigned resource, and **TaskStatus** (PENDING, FINISHED, CANCELED). Managed in a **BinarySearchTree** for O(log n) operations.

2. **Algorithms**
   - **MergeSort**: Sorts tasks by urgency (descending) then deadline (ascending) before display.

3. **Service Layer**
   - `SchedulerService` provides API for adding, updating, deleting, and querying resources and tasks. It also exposes bulk operations to clear finished or canceled tasks.

4. **User Interface**
   - Built with **JavaFX FXML** and styled via **CSS** (`styles.css`):
     - Split-pane layout: resources on the left, tasks on the right.
     - Editable table columns (e.g., task status via combo box cell factory).
     - Input forms for adding/updating resources and tasks.
     - Buttons to clear finished/canceled tasks.
   - **Animations**: Fade-in transition on application launch.
   - **Validation**: GUI-level checks for required fields, valid dates, and urgency values.

5. **Testing**
   - **JUnit 5** tests cover:
     - `HashTableTests`: insertion, update, removal, and key enumeration.
     - `BinarySearchTreeTests`: put/get/remove operations, in-order traversal.
     - `MergeSortTests`: sorting edge cases and larger lists.
     - `SchedulerServiceTests`: end-to-end service API behavior, prioritization logic.
   - Achieved over 90% unit-test coverage.

## Usage & Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/ChaseSunstrom/business-scheduler.git
   cd business-scheduler
   ```
2. Build and run with Gradle:
   ```bash
   ./gradlew build
   ./gradlew run
   ```
3. From the UI:
   - **Add Resource**: Enter ID, name, and type; click Add.
   - **Add/Update Task**: Enter task details; click Add/Update.
   - **Edit/Delete**: Right-click a row in tables for context-menu actions.
   - **Set Status**: Click the status cell and choose PENDING/FINISHED/CANCELED.
   - **Clear Finished/Canceled**: Use buttons below the task form.

## Repository & Demo
- GitHub: [https://github.com/ChaseSunstrom/business-scheduler](https://github.com/ChaseSunstrom/business-scheduler)
- Screenshots and a demo video are available in the `/screenshots` directory.

## Lessons Learned
- Proper FXML attribute binding requires explicit property elements (e.g., `<padding><Insets/></padding>`).
- JavaFX module dependencies must include base and graphics libraries with platform classifiers.
- Editable table cell factories are best configured in code, not in FXML.
- Consistent package structure is critical for resource loading and controller mapping.

## Future Work
- **Persistent Storage**: Integrate SQLite for saving/restoring schedules.
- **Undo/Redo** support for CRUD operations.
- **Calendar Integration**: Sync tasks with external calendars (Google, Outlook).
- **User Preferences**: Themes, date/time formats, and notification settings.
- **Web & Mobile Clients**: Expand to Spring Boot web service and native mobile apps.

---
*Prepared by Chase Sunstrom, 05/12/2025* 