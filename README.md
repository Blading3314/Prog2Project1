# Movie Rental Application - Programming 2 Final Project

This is a small movie-rental application (GUI + some console utility code) used to manage movies, students and members, and to handle simple rentals and payments. This README describes how to run the app, how IDs work, and where to find the source and data files.

## What this project does
- Manage movies, students, and external members.
- Rent and return movies with date validation.
- Simple pricing/payment logic and rental records.
- Lightweight JavaFX GUI for everyday use.

## Requirements
- Java 17+ (the project has been used with Amazon Corretto / OpenJDK 17+ / 22).
- JavaFX SDK matching your Java version (JavaFX 22 used in development).
- An IDE such as IntelliJ IDEA (recommended) or a build tool (Maven/Gradle) if you integrate one.

## Quick start — GUI (IntelliJ)
1. Install the JavaFX SDK and note its installation path.
2. In IntelliJ, open the project.
3. Edit the run configuration VM options and add:
   --module-path C:\path\to\javafx-sdk\lib --add-modules javafx.controls,javafx.fxml
4. Run the launcher class (e.g. launchers.AppStart) or the existing run configuration.

Notes:
- Replace C:\path\to\javafx-sdk with your actual JavaFX SDK folder.
- If you see errors about missing JavaFX classes, confirm the module path and modules in the VM options.

## Data files
Project data files live in the project's `data/` folder:
- data/Movies.json
- data/Students.json
- data/Members.json
- data/Rentals.json

Back up these files if you plan to edit them by hand.

## ID rules (how IDs behave in the UI)
- IDs shown in the UI are "external IDs" like `M1`, `ST5`, `MB12`.
- Users may enter either:
  - Plain numbers (e.g. `5`) — the app will normalize and prefix them automatically depending on context (students → `ST5`, members → `MB5`, movies → `M5`), or
  - Prefixed IDs (e.g. `ST05`, `m12`, `MB3`) — the app normalizes the prefix to uppercase and strips leading zeros (so `st009` → `ST9`).
- Allowed prefixes are `M`, `ST`, and `MB`. Other letters are rejected and the UI will show a helpful error message.
- When using the "Can't decide your ID?" option in add dialogs the app will auto-select a valid ID for the class (no random letters — deterministic sequence based on existing IDs).

## Important UI behaviors
- Date pickers: begin date defaults to today. Return date must be after the begin date and within 1 month.
- When entering IDs in rent/return forms, numeric-only inputs will be auto-prefixed and normalized for clarity.
- Rental records show renter name plus (ID) in the rentals tab.

## Troubleshooting
- JSONException on startup: if the app errors reading JSON, check that data/*.json is present and well-formed. The loader expects certain fields; if you edited JSON manually, ensure keys like `rentable` are present or use the app to regenerate data.
- JavaFX errors: confirm VM options include the correct module-path and modules for your JavaFX SDK version.
- If a user is charged as the wrong membership type, check the membership mapping logic in the controller — the app uses the actual membership type field when computing prices.

## Development notes
- Source code is under `/src` in the project root.
- Key packages:
  - javaFX — GUI and controllers
  - classes — domain models (Movie, Student, ExternalMember, MovieRental, etc.)
  - JSONHandling — load/save helper for data files

## Javadoc
View the generated Javadoc (if available) at:
https://blading3314.github.io/Prog2Project1/
