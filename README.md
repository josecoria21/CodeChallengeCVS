# Flickr Image Search App

This app is a coding challenge for a position as an Android Developer at CVS. It allows users to search for images from Flickr using a keyword. The app is built using modern Android development practices and libraries.

---

## Features

- **Search for Images**: Users can enter a keyword to search for images from Flickr.
- **Display Results**: The app displays a list of images matching the search query.
- **Image Details**: Users can tap on an image to view its details, including the title and description.
- **Modern Architecture**: Built using MVVM (Model-View-ViewModel) architecture for clean separation of concerns.
- **Responsive UI**: A clean and intuitive user interface built with Jetpack Compose.

---

## Technologies Used

- **Kotlin**: The primary programming language for the app.
- **Jetpack Compose**: For building the UI declaratively.
- **Retrofit**: For making network requests to the Flickr API.
- **Hilt**: For dependency injection.
- **StateFlow**: For managing and observing UI state.
- **Coil**: For loading and displaying images.
- **Material Design**: For a consistent and modern look and feel.

---

## Architecture

The app follows the **MVVM (Model-View-ViewModel)** architecture:

- **Model**: Represents the data layer, including API calls and data classes.
- **View**: The UI layer built with Jetpack Compose.
- **ViewModel**: Manages the UI state and business logic.

---

## Screenshots

| Search Screen | Results Screen | Details Screen |
|---------------|----------------|----------------|
| ![Screenshot 2025-01-14 at 8 57 10 p m](https://github.com/user-attachments/assets/5e446143-6b50-4eac-8528-09a8c52f5988) | ![Screenshot 2025-01-14 at 8 59 29 p m](https://github.com/user-attachments/assets/0a631258-800b-469f-996f-71afc1f1e9f5) | ![Screenshot 2025-01-14 at 9 00 11 p m](https://github.com/user-attachments/assets/21427fda-f03c-4a7f-9f4e-0be5251ec0e9) |


---

## How It Works

1. **Search for Images**:
   - The user enters a keyword in the search bar.
   - The app makes a network request to the Flickr API using Retrofit.
   - The results are displayed in a list of images.

2. **View Image Details**:
   - The user can tap on an image to view its details, including the title and description.

3. **State Management**:
   - The app uses `StateFlow` to manage the UI state (e.g., loading, success, error).
   - The `ViewModel` exposes the state to the UI layer.

4. **Image Loading**:
   - Images are loaded asynchronously using the **Coil** library.

---

## Dependencies

- **Retrofit**: For network requests.
- **Hilt**: For dependency injection.
- **Coil**: For image loading.
- **Jetpack Compose**: For UI.
- **Material Design**: For UI components.

---

## Future Improvements

- **Pagination**: Load more images as the user scrolls.
- **Error Handling**: Improve error handling and display user-friendly messages.
- **Caching**: Implement caching for offline support.
- **Unit Tests**: Add more unit tests for ViewModel and repository.
- **UI Enhancements**: Add animations and transitions for a better user experience.

---

## Acknowledgments

- **Flickr API**: For providing the image data.
- **Jetpack Compose**: For making UI development faster and more enjoyable.
- **Retrofit and Coil**: For simplifying network requests and image loading.

---

## Contact

If you have any questions or feedback, feel free to reach out:

- **Name**: Jose Coria
