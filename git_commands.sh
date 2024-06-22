#!/bin/bash

#!/bin/bash

# Initialize README.md with a header
echo "# All-Pattern-Crypto-Backend" >> README.md

# Initialize a Git repository
git init

# Add all existing files to the staging area
git add .

git rm .env

# Commit the changes with a message
git commit -m "Initial commit with all existing files"

# Rename the default branch to main
git branch -M main

# Add the remote repository
git remote add origin https://github.com/cm-harshpatel/ecommerce-backend.git

# Push the changes to the remote repository
git push -u origin main

