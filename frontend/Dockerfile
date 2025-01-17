# Dockerfile for Frontend (Vue 2)
FROM mirror.ccs.tencentyun.com/library/node:14-alpine


# Set working directory
WORKDIR /app

# Copy the entire frontend project to the container
COPY . /app

# Install dependencies
RUN npm install --registry=https://registry.npmmirror.com

# Expose the frontend port (assumed default port for Vue Dev Server)
EXPOSE 55552

# Run the development server
CMD ["npm", "run", "dev"]
