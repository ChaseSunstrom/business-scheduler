# Project Proposal and Report

## Introduction

Efficient resource management and error-free scheduling are critical to the success of any growing organization. Unfortunately, manual scheduling often leads to wasted time, resource conflicts, and missed deadlines. This project presents an **Intelligent Scheduling Application**, designed to streamline task assignment, optimize resource utilization, and minimize human errors by leveraging robust data structures and algorithms.

## Project Description

The Intelligent Scheduling Application is built on three core principles:

1. **Hash Table for Resource Storage**: All available resources (e.g., personnel, equipment, rooms) are stored in a hash table to enable O(1) insertion, deletion, and lookup operations.
2. **Binary Search Tree for Task Retrieval**: Scheduled tasks are organized in a binary search tree (BST) keyed by task ID, allowing efficient search, insertion, and deletion in O(log n) time on average.
3. **Merge Sort for Task Prioritization**: Before display, tasks are sorted by urgency and deadline using a stable merge sort algorithm to ensure that the most critical tasks are presented first.

The application features:

- A responsive GUI built with [Your GUI Framework] that allows end users to add, modify, and view schedules in real time.
- Full input validation at both the GUI layer and the data-structure API to prevent invalid operations.
- Extensive unit tests covering all data-structure operations and business logic.

## Time/Change Logs

### Sprint 1 (Week 1)
- Defined class interfaces for `Resource`, `Task`, `HashTable`, and `BinarySearchTree`.
- Implemented basic unit tests for insertion, deletion, and lookup in both data structures.
- Set up project scaffolding and continuous integration.

### Sprint 2 (Week 2)
- Designed and implemented the GUI skeleton, including main window, resource panel, and task list.
- Connected GUI inputs to backend data-structure API with placeholder data.
- Validated user inputs (e.g., non-empty names, valid dates) at the form level.

### Sprint 3 (Week 3)
- Integrated merge sort for dynamic task prioritization in the display list.
- Linked GUI elements to trigger scheduling operations (add, edit, delete tasks).
- Performed debugging across GUI and backend; fixed race conditions and rendering issues.

### Sprint 4 (Week 4)
- Completed end-to-end integration tests.
- Optimized hash table resizing and BST balancing for large data sets.
- Wrote comprehensive documentation, prepared final presentation slides, and recorded demo video.

## Lessons Learned

Throughout this project, the initial scope remained stable: to deliver a maintainable scheduling application with clear separation between GUI and core logic. Key blockers included:

- **Concurrency Issues**: GUI callbacks occasionally accessed shared data structures simultaneously, leading to inconsistent state.  
  *Solution*: Introduced thread-safe locking around critical sections and deferred long-running operations to background threads.

- **GUI Performance**: Rendering large task lists in the main thread caused frame drops.  
  *Solution*: Implemented incremental rendering and virtualized list controls.

- **Unit Test Coverage**: Edge cases in merge sort (e.g., empty lists, identical deadlines) were initially untested.  
  *Solution*: Expanded test suite to include boundary and stress tests, achieving over 90% coverage.

## Code and Repository

- GitHub Repository: **[Replace with your repository URL]**
- All source files include comprehensive comments following JavaDoc.  
- Project structure:
  ```
  src/
    |- DataStructures/HashTable.*
    |- DataStructures/BinarySearchTree.*
    |- Algorithms/MergeSort.*
    |- UI/MainForm.*
    |- Tests/HashTableTests.*
    |- Tests/BSTTests.*
  ```

## User Manual

### Prerequisites

- Java 11+
- [Your GUI Framework] runtime  
- Git (to clone repository)

### Installation & Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/username/intelligent-scheduler.git
   cd intelligent-scheduler
   ```
2. Build the project:
   ```bash
   ./gradlew build    # for Java/Gradle
   dotnet build       # for .NET Core
   ```

### Running the Application

- Launch the executable:
  ```bash
  ./runScheduler.sh   # Linux/Mac
  runScheduler.bat    # Windows
  ```

- The main window will appear with two panels: **Resources** (left) and **Scheduled Tasks** (right).

### GUI Walkthrough

1. **Add Resource**: Click **+** in the Resources panel, fill in details, and click **Save**.  
   ![Add Resource](screenshots/add_resource.png)

2. **Schedule Task**: Click **New Task** in the Tasks panel, select a resource, set urgency/ deadline, then click **Schedule**.  
   ![Schedule Task](screenshots/schedule_task.png)

3. **View Sorted Tasks**: Tasks are automatically ordered by urgency and deadline. Use the filter bar to search by task name or resource.  
   ![View Tasks](screenshots/view_tasks.png)

4. **Edit/Delete**: Right-click a task to edit or delete it. Confirm your changes in the dialog that appears.

## Conclusion and Summary

### MERUSE Principles Applied
- **Modularity**: Clear separation between data structures, business logic, and UI code.
- **Encapsulation**: All data operations are exposed through well-documented APIs.
- **Reusability**: Core data-structure implementations can be reused in future applications.
- **Usability**: Intuitive GUI with input validation to guide user workflows.
- **Simplicity & Efficiency**: Algorithms and data structures chosen for clear complexity guarantees.
- **Error Handling**: Comprehensive try/catch blocks and user-friendly error dialogs.

### Project Summary

The Intelligent Scheduling Application successfully demonstrates how combining hash tables, binary search trees, and merge sort can yield a responsive, accurate scheduling system. With unit tests ensuring robustness and a clean UI for everyday users, the system streamlines complex scheduling tasks.

---

*Prepared by Chase Sunstrom, 05/04/2025* 