# Login User Repository Setup

## Repository Configuration Steps

### 1. Initialize Git Repository
- Clone the repository from GitHub:
  ```sh
  git clone https://github.com/gauravgavhale2002/loginUserRepo.git
  ```
- Navigate to the project directory:
  ```sh
  cd loginUserRepo
  ```

### 2. Rename Default Branch
- Check the current branch:
  ```sh
  git branch -a
  ```
- Rename `main` to `main-public`:
  ```sh
  git branch -m main main-public
  ```
- Push the renamed branch to remote:
  ```sh
  git push origin -u main-public
  ```
- Set `main-public` as the default branch on GitHub (manually via GitHub settings).

### 3. Create a Feature Branch
- Create and switch to a new feature branch:
  ```sh
  git checkout -b feature/backend-login-user-check
  ```
- Push the feature branch to remote:
  ```sh
  git push origin feature/backend-login-user-check
  ```

### 4. Verify Branches
- List all branches:
  ```sh
  git branch -a
  ```
- Ensure `main-public` is the main branch and `feature/backend-login-user-check` exists.

### 5. Update Repository Information
- Modify the `README.md` file to document these changes.
- Commit and push the changes:
  ```sh
  git add README.md
  git commit -m "Updated README with repository setup steps"
  git push origin main-public
  ```

## Notes
- **Do not delete the `main` branch forcefully** as GitHub needs a default branch.
- Always work on feature branches and merge them into `main-public` via pull requests.
- Keep the repository structure clean and well-documented.

---
✅ **Setup Completed Successfully!**
