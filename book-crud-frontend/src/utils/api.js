import axios from "axios";

const API_BASE_URL = "http://localhost:8080/book";

export const getBooks = async () => {
    try {
        const response = await axios.get(`${API_BASE_URL}/books`);
        console.log("Books fetched:", response); // ✅ Debug API response
        return response; // ✅ Return the full Axios response
    } catch (error) {
        console.error("Axios error:", error);
        throw error; // ✅ Ensure errors propagate properly
    }
};

export const getBooksBySearchCriteria = async (searchTitle, searchAuthor) => {
    try {
        console.log(`🔍 Searching books with title: "${searchTitle}" and author: "${searchAuthor}"`);

        const response = await axios.get(`${API_BASE_URL}/books/search`, {
            params: { title: searchTitle, author: searchAuthor }
        });

        console.log("✅ Books fetched:", response.data); // ✅ Log the response
        return response;
    } catch (error) {
        if (error.response) {
            console.error(`❌ Axios error ${error.response.status}:`, error.response.data);
        } else {
            console.error("❌ Axios request error:", error.message);
        }
        throw error;
    }
};

export const getBookById = (id) => axios.get(`${API_BASE_URL}/books/${id}`);
export const addBook = (book) => axios.post(`${API_BASE_URL}/createBook`, book);
export const updateBook = (id, book) => axios.put(`${API_BASE_URL}/books/${id}`, book);
export const deleteBook = async (id) => {
    try {
        //alert("id for deleting in api" + id) ;
        const response = await axios.delete(`${API_BASE_URL}/books/${id}` );
        console.log("Book deleted:", response.data); // ✅ Debugging
        return response.data;
    } catch (error) {
        console.error("Error deleting book:", error);
        throw error; // ✅ Ensure errors propagate properly
    }
};
export const getAIInsights = (id) => axios.post(`${API_BASE_URL}/${id}/ai-insights`);
