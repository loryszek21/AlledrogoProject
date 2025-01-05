import Table from "../Table/Table";

function OrderTable() {
  const orderItems = [
    { id: 1, product: "Product A", date: "2024-12-01", status: "Delivered" },
    { id: 2, product: "Product B", date: "2024-12-05", status: "Pending" },
  ];

  return (
    <Table
      title="Previous Orders"
      items={orderItems}
      headers={["Product", "Date", "Status", "Price","Invoice" ]}
      renderRow={(item, index) => (
        <tr key={item.id || index}>
          <td className="p-4">{item.product}</td>
          <td className="p-4">{item.date}</td>
          <td className="p-4">{item.status}</td>
          <td className="p-4">{item.price}</td>
          <td className="p-4">{item.invoice}</td>
        </tr>
      )}
    />
  );
}

export default OrderTable;
