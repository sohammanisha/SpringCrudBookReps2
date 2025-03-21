"use client"; // ✅ Ensure it runs as a client component
import { useEffect, useState, Suspense } from "react";
import Link from "next/link";
import { getBooks, deleteBook, getAIInsights, getBooksBySearchCriteria } from "../utils/api";
import { useRouter } from "next/navigation";

export default function Home() {
    const [books, setBooks] = useState([]);
    const [loading, setLoading] = useState(true);
    const [searchAuthor, setSearchAuthor] = useState(null);
    const [searchTitle, setSearchTitle] = useState(null);

    const router = useRouter();

    useEffect(() => {
        fetchBooks();
    }, []);

    const fetchBooks = async () => {
        try {
            const response = await getBooks();
            setBooks(Object.values(response.data));
        } catch (error) {
            console.error("Error fetching books:", error);
        } finally {
            setLoading(false);
        }
    };

    const handleDelete = async (id) => {
        if (!window.confirm("Are you sure you want to delete this book?")) return;

        try {
            await deleteBook(id);
            fetchBooks();
        } catch (error) {
            console.error("Failed to delete book:", error);
            alert("Error deleting book. Please try again.");
        }
    };
    const handleSearch = async () => {
        try {
            console.log("🔍 Triggering search with:", { searchTitle, searchAuthor });

            const response = await getBooksBySearchCriteria(searchTitle, searchAuthor);
            console.log("✅ Search results:", response.data);

            setBooks(response.data); // ✅ Expecting an array directly
        } catch (error) {
            console.error("❌ Error searching books:", error);
            alert("Failed to fetch search results. Check the console for details.");
        }
    };

    return (
        <div className="max-w-4xl mx-auto py-8">
            <h1 className="text-4xl font-bold mb-6 text-center">Book List</h1>

            <div className="flex justify-between items-center mb-4">
                <Link href="/add-book" className="bg-blue-500 text-white px-4 py-2 rounded-lg shadow-md hover:bg-blue-600">
                    ➕ Add Book
                </Link>
            </div>

            <div className="flex gap-4 mb-4">
                <input
                    type="text"
                    placeholder="Search by title"
                    value={searchTitle}
                    onChange={(e) => setSearchTitle(e.target.value)}
                    className="p-2 border rounded-md w-1/2"
                />
                <input
                    type="text"
                    placeholder="Search by author"
                    value={searchAuthor}
                    onChange={(e) => setSearchAuthor(e.target.value)}
                    className="p-2 border rounded-md w-1/2"
                />
                <button
                    onClick={handleSearch}
                    className="bg-green-500 text-white px-4 py-2 rounded-md shadow-md hover:bg-green-600"
                >
                    🔍 Search
                </button>
            </div>

            {loading ? (
                <SkeletonLoader />
            ) : (
                <table className="table-auto w-full border-collapse border border-gray-300 shadow-lg">
                    <thead>
                    <tr className="bg-gray-200 text-gray-700">
                        <th className="border p-3">Title</th>
                        <th className="border p-3">Author</th>
                        <th className="border p-3">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {books.length > 0 ? (
                        books.map((book) => (
                            <tr key={book.bookID} className="hover:bg-gray-100 transition">
                                <td className="border p-3">{book.bookName}</td>
                                <td className="border p-3">{book.author}</td>
                                <td className="border p-3 flex gap-2">
                                    <Link href={`/edit-book/${book.bookID}`} className="bg-yellow-500 text-white px-3 py-1 rounded-md hover:bg-yellow-600">
                                        ✏ Edit
                                    </Link>
                                    <button onClick={() => handleDelete(book.bookID)} className="bg-red-500 text-white px-3 py-1 rounded-md hover:bg-red-600">
                                        ❌ Delete
                                    </button>
                                </td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan="3" className="text-center py-4 text-gray-500">
                                No books available.
                            </td>
                        </tr>
                    )}
                    </tbody>
                </table>
            )}
        </div>
    );
}

// ✅ Skeleton Loader for better UI
function SkeletonLoader() {
    return (
        <div className="space-y-3 animate-pulse">
            <div className="h-6 bg-gray-300 rounded w-1/3"></div>
            <div className="h-6 bg-gray-300 rounded w-2/3"></div>
            <div className="h-6 bg-gray-300 rounded w-1/2"></div>
        </div>
    );
}
