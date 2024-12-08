import re

def replace_digits(file_path):
    with open(file_path, 'r') as file:
        content = file.read()

    # Replace single digits with double digits
    content = re.sub(r'\b(\d)\b', r'0\1', content)

    with open(file_path, 'w') as file:
        file.write(content)

# List of files to process
files = ['OASIS.TXT', 'LAND.TXT', 'MAZE.TXT']

for file in files:
    replace_digits(file)