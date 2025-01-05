function Table({ title, items, renderRow, headers }) {
    return (
      <div className="lg:col-span-2 p-6 bg-white dark:bg-dark dark:text-white overflow-x-auto">
        <div className="flex gap-2 border-b pb-4">
          <h2 className="text-2xl font-bold text-dark dark:bg-dark dark:text-white flex-1">
            {title}
          </h2>
          <h3 className="text-base text-dark dark:bg-dark dark:text-white">
            {items.length} Items
          </h3>
        </div>
  
        <table className="mt-6 w-full border-collapse divide-y">
          <thead className="whitespace-nowrap text-left">
            <tr>
              {headers.map((header, index) => (
                <th
                  key={index}
                  className="text-base text-dark dark:bg-dark dark:text-white p-4"
                >
                  {header}
                </th>
              ))}
            </tr>
          </thead>
  
          <tbody className="whitespace-nowrap divide-y">
            {items.map((item, index) => renderRow(item, index))}
          </tbody>
        </table>
      </div>
    );
  }
  
  export default Table;
  