"use client";
import { useEffect, useState } from "react";
import { getBookById, updateBook } from "../../utils/api";
import { useRouter } from "next/router";
import Link from "next/link";

export default function EditBook() {
    const [book, setBook] = useState({
        bookName: "", author: "", bookISBN: "", bookType: "",
        date: "", editionNumber: "", description: ""
    });

    const router = useRouter();
    const { id } = router.query;

    useEffect(() => {
        if (!id) return;
        getBookById(id)
            .then((response) => setBook(response.data))
            .catch((error) => console.error("Error fetching book:", error));
    }, [id]);

    const handleChange = (e) => setBook({ ...book, [e.target.name]: e.target.value });

    const handleSubmit = async (e) => {
        e.preventDefault();
        await updateBook(id, book);
        router.push("/");
    };

    return (
        <div className="max-w-2xl mx-auto py-8">
            <h1 className="text-4xl font-bold text-center mb-6 text-gray-800">Edit Book</h1>
            <div className="bg-white shadow-lg rounded-lg p-6 border border-gray-200">
                <form onSubmit={handleSubmit} className="space-y-4">
                    <div className="grid grid-cols-1 gap-4">
                        <label className="block">
                            <span className="text-gray-700">Book Title</span>
                            <input type="text" name="bookName" value={book.bookName || ""} onChange={handleChange} className="w-full mt-1 p-2 border rounded-md focus:ring-2 focus:ring-blue-400" required />
                        </label>
                        <label className="block">
                            <span className="text-gray-700">Author</span>
                            <input type="text" name="author" value={book.author || ""} onChange={handleChange} className="w-full mt-1 p-2 border rounded-md focus:ring-2 focus:ring-blue-400" required />
                        </label>
                        <label className="block">
                            <span className="text-gray-700">ISBN</span>
                            <input type="text" name="bookISBN" value={book.bookISBN || ""} onChange={handleChange} className="w-full mt-1 p-2 border rounded-md focus:ring-2 focus:ring-blue-400" required />
                        </label>
                        <label className="block">
                            <span className="text-gray-700">Type</span>
                            <input type="text" name="bookType" value={book.bookType || ""} onChange={handleChange} className="w-full mt-1 p-2 border rounded-md focus:ring-2 focus:ring-blue-400" required />
                        </label>
                        <label className="block">
                            <span className="text-gray-700">Publish Date</span>
                            <input type="date" name="date" value={book.date || ""} onChange={handleChange} className="w-full mt-1 p-2 border rounded-md focus:ring-2 focus:ring-blue-400" required />
                        </label>
                        <label className="block">
                            <span className="text-gray-700">Edition Number</span>
                            <input type="text" name="editionNumber" value={book.editionNumber || ""} onChange={handleChange} className="w-full mt-1 p-2 border rounded-md focus:ring-2 focus:ring-blue-400" required />
                        </label>
                        <label className="block">
                            <span className="text-gray-700">Description</span>
                            <textarea name="description" value={book.description || ""} onChange={handleChange} className="w-full mt-1 p-2 border rounded-md focus:ring-2 focus:ring-blue-400" required />
                        </label>
                    </div>
                    <div className="flex justify-between mt-4">
                        <button type="submit" className="bg-blue-500 text-white px-4 py-2 rounded-md shadow-md hover:bg-blue-600 transition">
                            ✅ Update Book
                        </button>
                        <Link href="/" className="bg-gray-500 text-white px-4 py-2 rounded-md shadow-md hover:bg-gray-600 transition">
                            🔙 Back
                        </Link>
                    </div>
                </form>
            </div>
        </div>
    );
}
