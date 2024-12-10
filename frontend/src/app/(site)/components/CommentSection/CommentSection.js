import { useState } from "react";

const CommentSection = () => {
  const [comments, setComments] = useState([
    {
      id: 1,
      user: "John Doe",
      rating: 4,
      text: "Great product! Highly recommend it.",
      date: "2024-12-10",
    },
    {
      id: 2,
      user: "Jane Smith",
      rating: 5,
      text: "Absolutely fantastic! Worth every penny.",
      date: "2024-12-08",
    },
  ]);

  const [newComment, setNewComment] = useState("");
  const [rating, setRating] = useState(5);

  const handleAddComment = () => {
    if (!newComment.trim()) return;

    const newEntry = {
      id: comments.length + 1,
      user: "Guest User", // In real apps, this will come from authentication
      rating,
      text: newComment,
      date: new Date().toISOString().split("T")[0],
    };

    setComments([newEntry, ...comments]);
    setNewComment("");
    setRating(5);
  };

  return (
    <div className="max-w-3xl mx-auto p-4 space-y-6 bg-white rounded-lg shadow-md dark:bg-dark">
      <h2 className="text-xl font-bold text-gray-800 dark:text-white">Comments & Reviews</h2>

      {/* Add Comment Section */}
      <div className="space-y-4">
        <textarea
          value={newComment}
          onChange={(e) => setNewComment(e.target.value)}
          placeholder="Write your comment here..."
          className="w-full p-3 border rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-ring_color dark:bg-medium dark:text-white "
        />

        <div className="flex items-center space-x-4">
          <select
            value={rating}
            onChange={(e) => setRating(Number(e.target.value))}
            className="p-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-ring_color dark:bg-medium dark:text-white"
          >
            {[1, 2, 3, 4, 5].map((star) => (
              <option key={star} value={star}>
                {star} Star{star > 1 ? "s" : ""}
              </option>
            ))}
          </select>
          <button
            onClick={handleAddComment}
            className="px-4 py-2 font-semibold text-white bg-button rounded-lg hover:bg-buttonHover"
          >
            Submit
          </button>
        </div>
      </div>

      {/* Comments List */}
      <div className="space-y-4">
        {comments.map((comment) => (
          <div
            key={comment.id}
            className="p-4 border rounded-lg bg-gray-50 dark:bg-medium dark:text-white"
          >
            <div className="flex items-center justify-between">
              <div>
                <h3 className="text-sm font-semibold">{comment.user}</h3>
                <p className="text-xs text-gray-500 dark:text-gray-400">{comment.date}</p>
              </div>
              <div className="flex space-x-1">
                {[...Array(5)].map((_, i) => (
                  <span
                    key={i}
                    className={`w-4 h-4 ${i < comment.rating ? "text-yellow-400" : "text-gray-300"} fill-current`}
                  >
                    ★
                  </span>
                ))}
              </div>
            </div>
            <p className="mt-2 text-sm">{comment.text}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default CommentSection;
