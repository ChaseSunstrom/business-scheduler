# Business Scheduler Project Report

## Proposal

### Introduction

The Business Scheduler is a JavaFX desktop application that provides intuitive resource management and task scheduling by leveraging in-memory data structures, efficient algorithms, and a responsive user interface. This proposal outlines the goals, project scope, architecture, and timeline for development.

### Project Goals

- Implement in-memory **HashTable** for managing Resources (personnel, equipment, rooms).
- Use **Binary Search Tree** for storing and organizing Tasks by ID.
- Apply **MergeSort** to prioritize tasks by urgency and deadline.
- Develop an interactive JavaFX UI with FXML, CSS styling, and animations.
- Achieve high code quality with JUnit 5 tests and maintainable architecture.

### Application Architecture

1. **Models**:  
   - `Resource`: ID, name, type.  
   - `Task`: ID, name, urgency (1–5), deadline, assigned resource, status (`PENDING`, `FINISHED`, `CANCELED`).
2. **Data Structures**:  
   - `HashTable<K,V>` for O(1) resource operations.  
   - `BinarySearchTree<K,V>` for O(log n) task operations.
3. **Algorithms**:  
   - `MergeSort` for sorting Task lists by urgency and deadline.
4. **UI**:  
   - JavaFX FXML with a split-pane layout: Resources on the left, Tasks on the right.  
   - Editable table columns, form inputs, clear buttons, and fade-in launch animation.  
   - Styled with `styles.css`.
5. **Service Layer**:  
   - `SchedulerService`: provides CRUD operations and bulk clear methods.
6. **Testing**:  
   - JUnit 5 tests covering data structures, algorithms, service layer, and UI controllers.

### Development Timeline (Mar 10 – May 3, 2025)

This project will follow an 8-week development schedule:

- **Weeks 1-2**: Set up project, implement models and data structures.  
- **Weeks 3-4**: Develop MergeSort, service layer, and core UI screens.  
- **Weeks 5-6**: Enhance UI (CSS, animations), implement task prioritization and clear functions, write tests.  
- **Weeks 7-8**: Polish, perform end-to-end testing, prepare documentation, and presentation.

## Time and Change Log

This section outlines the weekly development progress for the Business Scheduler JavaFX desktop application (March 10 – May 3, 2025).

### Week 1: March 10 – March 16, 2025

- Project setup: configured Gradle, JavaFX dependencies, and repository structure.  
- Defined domain models for `Resource` and `Task`.  
- Drafted initial FXML layout for resource and task panes.

### Week 2: March 17 – March 23, 2025

- Implemented `HashTable<K, V>` for fast Resource management with unit tests.  
- Developed `BinarySearchTree<K, V>` for Task storage and operations.  
- Established JUnit 5 test framework and wrote core data structure tests.

### Week 3: March 24 – March 30, 2025

- Built `Task` and `Resource` service layer (`SchedulerService`) with CRUD methods.  
- Integrated `MergeSort` algorithm for task prioritization by urgency and deadline.  
- Added unit tests for service methods and sorting logic.

### Week 4: March 31 – April 6, 2025

- Created core JavaFX UI screens using FXML: split-pane with Resources and Tasks tables.  
- Implemented forms for adding/updating items, context menus, and clear buttons.  
- Applied CSS styling (`styles.css`) and fade-in animation on launch.

### Week 5: April 7 – April 13, 2025

- Connected UI controls with the service layer: data binding and event handlers.  
- Validated user inputs in controllers and displayed error dialogs.  
- Enhanced layout responsiveness and tested on different screen sizes.

### Week 6: April 14 – April 20, 2025

- Conducted integration testing between UI and backend services.  
- Identified and fixed UI bugs: table editing edge cases and layout inconsistencies.  
- Optimized performance by offloading heavy operations to JavaFX background threads.

### Week 7: April 21 – April 27, 2025

- Prepared distribution: configured the Gradle `application` plugin for a fat JAR.  
- Tested the run configurations and cross-platform JavaFX module setups.  
- Drafted the developer guide and updated the main README.

### Week 8: April 28 – May 3, 2025

- Completed the user manual and lessons learned documentation.  
- Drafted and rehearsed the presentation slides for demonstration.

## Lessons Learned

- Proper FXML attribute binding requires explicit property elements (e.g., `<padding><Insets/></padding>`) to avoid null property errors.  
- JavaFX module dependencies must specify base, controls, graphics, and fxml modules with the correct platform classifier (`win`, `mac`, `linux`) to satisfy module-path requirements.  
- Editable table cell factories (e.g., `ComboBoxTableCell`) are more reliable when configured in controller code rather than pure FXML.  
- Consistent package and resource organization is critical to ensure the FXML loader can locate `.fxml` and `.css` files at runtime.  
- Custom `HashTable` and `BinarySearchTree` implementations require careful null checks and balancing logic to maintain performance.  
- Offloading heavy tasks (sorting, bulk operations) to JavaFX background `Task` threads prevents UI freezes and maintains responsiveness.  
- Separating UI (FXML + controllers) from the `SchedulerService` layer improved testability, maintainability, and code clarity.

## Conclusion

### Principles of Good Programming (MERUSE)

## Modularity
- Structure the codebase into well-defined modules and layers (API, business logic, data access) so each component can evolve independently.  
- Define clear interfaces and abstractions that allow new calendar integrations or collaboration features to be added without touching existing core functionality.

## Efficiency
- Optimize critical algorithms and data structures (e.g., interval trees for conflict detection) to keep response times low.  
- Employ caching strategies, minimize database round-trips, and profile the system under realistic load scenarios to eliminate bottlenecks.

## Readable
- Follow consistent naming conventions and coding standards so that anyone scanning the code can immediately grasp its purpose.  
- Write self-documenting code, supplement it with concise comments where necessary, and enforce style rules through automated linting and regular code reviews.

## Usability
- Design the user interface around real workflows, providing intuitive navigation, clear feedback on actions, and contextual help.  
- Ship accessible documentation and in-app guidance to flatten the learning curve and ensure that both new and power users can accomplish their tasks with minimal friction.

## Stability
- Guard the system against unexpected failures and security threats by validating and sanitizing all inputs.  
- Enforce authentication and authorization (e.g., via JWT or OAuth2), and run comprehensive automated tests (unit, integration, load) before every release.

## Elegant
- Aim for simplicity and clarity in both code and user experience.  
- Refactor to remove duplication, choose expressive abstractions over clever hacks, and craft APIs and UI elements that feel natural and cohesive.  
- Continuously refine to keep the system both powerful and pleasing to work with.

### Project Summary

The Business Scheduler JavaFX desktop application delivers a responsive in-memory scheduling tool for managing Resources and Tasks. Core components include:

- **Data Structures**: `HashTable` for Resources (O(1) operations) and `BinarySearchTree` for Tasks (O(log n) operations).  
- **Algorithms**: `MergeSort` automatically prioritizes tasks by urgency and deadline.  
- **UI**: JavaFX FXML with CSS styling, editable tables, form inputs, clear-finished/canceled buttons, and fade-in animations.  
- **Service Layer**: `SchedulerService` provides clean CRUD APIs and bulk operations.  
- **Quality**: Over 90% unit-test coverage with JUnit 5 across data structures, algorithms, service layer, and controllers.

### Future Versions

Planned enhancements for future releases:

- **Persistent Storage**: Integrate SQLite or file-based persistence to retain data across sessions.  
- **Undo/Redo Support**: Track user actions and allow rollback of changes in Resources and Tasks.  
- **External Calendar Sync**: Add Google Calendar and Outlook integration for two-way event synchronization.  
- **User Preferences & Themes**: Offer customizable color schemes, date/time formats, and notification settings.  
- **Web & Mobile Clients**: Provide complementary Spring Boot REST service and native mobile frontends for broader access. 