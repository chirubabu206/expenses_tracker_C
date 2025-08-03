document.addEventListener('DOMContentLoaded', () => {
    fetchExpenses();
    fetchReports(); // New line to call the reporting function
    document.getElementById('expense-form').addEventListener('submit', handleFormSubmit);
});

const API_URL = 'http://localhost:8080/api/expenses';

async function fetchExpenses() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const expenses = await response.json();
        displayExpenses(expenses);
    } catch (error) {
        console.error('Error fetching expenses:', error);
    }
}

function displayExpenses(expenses) {
    const expenseList = document.getElementById('expense-list');
    expenseList.innerHTML = '';
    expenses.forEach(expense => {
        const row = `<tr>
            <td>${expense.id}</td>
            <td>${expense.description}</td>
            <td>${expense.amount}</td>
            <td>${expense.date}</td>
            <td>${expense.category}</td>
        </tr>`;
        expenseList.innerHTML += row;
    });
}

async function handleFormSubmit(event) {
    event.preventDefault();
    const form = event.target;
    const expense = {
        description: form.description.value,
        amount: parseFloat(form.amount.value),
        date: form.date.value,
        category: form.category.value
    };

    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(expense)
        });
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        form.reset();
        fetchExpenses(); // Refresh the list
        fetchReports(); // Refresh the report data
    } catch (error) {
        console.error('Error adding expense:', error);
    }
}

async function fetchReports() {
    try {
        const response = await fetch(`${API_URL}/report/category`);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        const categoryTotals = await response.json();
        renderChart(categoryTotals);
    } catch (error) {
        console.error('Error fetching report data:', error);
    }
}

function renderChart(categoryTotals) {
    const categories = categoryTotals.map(item => item.category);
    const amounts = categoryTotals.map(item => item.totalAmount);

    const ctx = document.getElementById('category-chart').getContext('2d');

    new Chart(ctx, {
        type: 'pie',
        data: {
            labels: categories,
            datasets: [{
                label: 'Amount',
                data: amounts,
                backgroundColor: [
                    '#9932CC', '#36a2eb', '#cc65fe', '#ff6384', '#ff9f40'
                ]
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top',
                },
                title: {
                    display: false
                }
            }
        }
    });
}