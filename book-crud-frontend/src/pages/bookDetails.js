import { useEffect, useState } from "react";
import { getBookById, getAIInsights } from "../../utils/api";
import { useRouter } from "next/router";

export default function BookDetails() {
    const [book, setBook] = useState(null);
    const [loading, setLoading] = useState(true);
    const [aiInsight, setAIInsight] = useState("");
    const [aiLoading, setAILoading] = useState(false);
    const [error, setError] = useState("");
    const router = useRouter();
    const { id } = router.query;

    useEffect(() => {
        if (id) {
            fetchBookDetails(id);
        }
    }, [id]);

    const fetchBookDetails = async (bookId) => {
        try {
            const response = await getBookById(bookId);
            setBook(response.data);
        } catch (error) {
            setError("Book not found.");
        } finally {
            setLoading(false);
        }
    };

    const fetchAIInsights = async () => {
        setAILoading(true);
        try {
            const response = await getAIInsights(id);
            setAIInsight(response.data.aiInsight);
        } catch (error) {
            setError("Failed to fetch AI insights.");
        } finally {
            setAILoading(false);
        }
    };

    if (loading) return <p>Loading book details...</p>;
    if (error) return <p className="text-red-500">{error}</p>;

    return (
        <div className="container mx-auto p-4">
            <h1 className="text-3xl font-bold">{book.bookName}</h1>
            <p><strong>Author:</strong> {book.author}</p>
            <p><strong>Published:</strong> {new Date(book.date).toLocaleDateString()}</p>
            <p><strong>ISBN:</strong> {book.bookISBN}</p>
            <p><strong>Description:</strong> {book.description}</p>

            <button
                onClick={fetchAIInsights}
                className="bg-green-500 text-white p-2 rounded mt-4"
                disabled={aiLoading}
            >
                {aiLoading ? "Loading..." : "Get AI Insights"}
            </button>

            {aiInsight && <p className="mt-4 text-blue-600"><strong>AI Insight:</strong> {aiInsight}</p>}
        </div>
    );
}
