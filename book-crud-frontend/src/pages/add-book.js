import { useState } from "react";
import { addBook } from "../utils/api";
import { useRouter } from "next/router";

export default function AddBook() {
    const [book, setBook] = useState({ bookName: "", author: "" });
    const router = useRouter();

    const handleChange = (e) => setBook({ ...book, [e.target.name]: e.target.value });

    const handleSubmit = async (e) => {
        e.preventDefault();
        await addBook(book);
        router.push("/");
    };

    return (
        <div className="container mx-auto p-4">
            <h1 className="text-3xl font-bold mb-4">Add Book</h1>

            <form onSubmit={handleSubmit} className="flex flex-col gap-4">
                <table>
                    <tr><td><input type="text" name="bookName" placeholder="Book Name" className="border p-2" onChange={handleChange} required /> </td></tr>
                    <tr><td><input type="text" name="bookISBN" placeholder="Book ISBN" className="border p-2" onChange={handleChange} required /></td></tr>
                    <tr><td><input type="text" name="bookPublisher" placeholder="Book Publisher" className="border p-2" onChange={handleChange} required /></td></tr>
                    <tr><td><input type="text" name="bookType" placeholder="Book Type" className="border p-2" onChange={handleChange} required /></td></tr>

                    <tr><td><input type="text" name="author" placeholder="Author" className="border p-2" onChange={handleChange} required /></td></tr>
                    <tr><td><input type="date" name="date" placeholder="Date" className="border p-2" onChange={handleChange} required /></td></tr>
                    <tr><td><input type="text" name="editionNumber" placeholder="Edition" className="border p-2" onChange={handleChange} required /></td></tr>
                    <tr><td><input type="text" name="description" placeholder="Description" className="border p-2" onChange={handleChange} required /></td></tr>
                    <tr><td><button type="submit" className="bg-blue-500 text-white p-2 rounded">Save</button></td></tr>
                </table>
            </form>

        </div>
    );
}
