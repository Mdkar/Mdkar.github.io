# Requirements Document

## Introduction

The Website Content Manager helps manage content on a Jekyll-based academic website (mdkar.github.io). It enables AI assistants in future chat sessions to easily add, update, and manage website content including projects, publications, teaching materials, talks, music/art entries, and media assets while maintaining Jekyll conventions and consistent formatting.

## Glossary

- **Content Manager**: The system that processes user content requests and generates properly formatted Jekyll files
- **Jekyll Site**: The static website built using Jekyll and the Academic Pages theme
- **Collection**: A Jekyll content type (projects, publications, teaching, talks, musicart)
- **Frontmatter**: YAML metadata at the beginning of markdown files enclosed in triple dashes
- **Permalink**: The URL path where a page will be accessible on the website
- **Asset**: Media files such as images, videos, or documents stored in the /images/ directory
- **Content File**: A markdown file with YAML frontmatter stored in a collection directory
- **Validation Rules**: Format requirements for frontmatter fields, permalinks, and dates

## Requirements

### Requirement 1: Project Content Management

**User Story:** As a website owner, I want to add new projects to my website, so that I can showcase my work to visitors

#### What the system does

1. When you provide project details (title, description, date, links, tech stack), the system creates a markdown file in the _projects/ directory
2. It generates frontmatter with these fields: title, excerpt, collection, permalink, date
3. Permalinks are formatted as /projects/{kebab-case-title}/
4. Dates follow the format YYYY-M-D (e.g., 2023-7-16)
5. If you provide an excerpt, it gets included in the frontmatter
6. Descriptions go in the markdown body
7. Links are formatted as markdown links in the body
8. Tech stack information is included in the markdown body

### Requirement 2: Project Updates

**User Story:** As a website owner, I want to update existing projects, so that I can keep my portfolio current

#### What the system does

1. When you request to update a project, the system locates the existing markdown file by title or permalink
2. When updating frontmatter fields, it preserves all other frontmatter fields
3. Body content gets replaced or appended based on what you specify
4. If a project file doesn't exist, the system lets you know and offers to create a new one

### Requirement 3: Media Asset Management

**User Story:** As a website owner, I want to add images and videos to my projects, so that I can visually showcase my work

#### What the system does

1. Image files get placed in the /images/ directory
2. Image filenames use descriptive kebab-case format
3. Images are embedded in markdown using: `<img src='/images/{filename}' style='height: 300px'>`
4. YouTube videos use iframe embed code with width="768" height="543"
5. If you provide custom image dimensions, they're applied to the style attribute
6. The system supports common image formats: jpg, jpeg, png, gif, svg

### Requirement 4: Publications Management

**User Story:** As an academic, I want to add publications to my website, so that I can share my research

#### What the system does

1. When you provide publication details, the system creates a markdown file in the _publications/ directory
2. It generates frontmatter with these fields: title, excerpt, collection, permalink, date
3. Permalinks are formatted as /publications/{kebab-case-title}/
4. Citation information gets formatted in the markdown body
5. Paper links or PDFs are included as markdown links

### Requirement 5: Teaching Content Management

**User Story:** As an educator, I want to add teaching materials to my website, so that students can access course information

#### What the system does

1. When you provide teaching content, the system creates a markdown file in the _teaching/ directory
2. It generates frontmatter with these fields: title, excerpt, collection, permalink, date
3. Permalinks are formatted as /teaching/{kebab-case-title}/
4. Course materials or syllabi links get included in the markdown body

### Requirement 6: Talks Management

**User Story:** As a speaker, I want to add talks to my website, so that I can showcase my presentations

#### What the system does

1. When you provide talk details, the system creates a markdown file in the _talks/ directory
2. It generates frontmatter with these fields: title, excerpt, collection, permalink, date
3. Permalinks are formatted as /talks/{kebab-case-title}/
4. Talk slides or recordings are included as links in the markdown body

### Requirement 7: Music and Art Content Management

**User Story:** As an artist, I want to add music and art projects to my website, so that I can share my creative work

#### What the system does

1. When you provide music or art content, the system creates a markdown file in the _musicart/ directory
2. It generates frontmatter with these fields: title, excerpt, collection, permalink, date
3. Permalinks are formatted as /musicart/{kebab-case-title}/
4. Media files get placed in the /images/ directory and embedded appropriately

### Requirement 8: Frontmatter Validation

**User Story:** As a website owner, I want all content files to have valid frontmatter, so that Jekyll can build my site correctly

#### What the system does

1. The system validates that all frontmatter is enclosed in triple dashes (---)
2. It checks that the collection field matches the directory name
3. It validates that permalinks follow the format /{collection}/{slug}/
4. It checks that dates follow the format YYYY-M-D or YYYY-MM-DD
5. When validation fails, the system reports the specific error
6. It ensures the title field is always present and non-empty

### Requirement 9: Profile Information Updates

**User Story:** As a website owner, I want to update my bio and profile information, so that visitors see current information about me

#### What the system does

1. When you request to update profile information, the system modifies the author section in _config.yml
2. It preserves all other configuration settings when updating _config.yml
3. When updating the avatar, it places the image in /images/ and updates the avatar field
4. When updating social media links, it updates the corresponding fields in the author section
5. It validates that _config.yml remains valid YAML after updates

### Requirement 10: Filename Generation

**User Story:** As a website owner, I want consistent filenames for my content, so that my repository is organized

#### What the system does

1. The system generates filenames in kebab-case format from the title
2. It uses the .md extension for all content files
3. When a filename already exists, it appends a number suffix (e.g., project-name-2.md)
4. It removes special characters from filenames except hyphens
5. It converts spaces to hyphens in filenames

### Requirement 11: Content Template Consistency

**User Story:** As a website owner, I want all content to follow consistent formatting, so that my website looks professional

#### What the system does

1. The system follows the existing formatting patterns observed in _projects/ files
2. When creating image embeds in excerpts, it uses the format: `<img src='/images/{filename}' style='height: 300px'>`
3. When creating video embeds, it uses iframe tags with consistent dimensions
4. It preserves markdown formatting conventions (headers, lists, links, code blocks)
5. It uses two newlines to separate sections in the markdown body

### Requirement 12: Error Handling and User Feedback

**User Story:** As a website owner, I want clear feedback when something goes wrong, so that I can correct issues

#### What the system does

1. When required information is missing, the system prompts you for the missing details
2. When a file operation fails, it reports the specific error
3. When validation fails, it explains what's invalid and how to fix it
4. It confirms successful operations with a summary of what was created or updated
5. When multiple issues are detected, it reports all issues at once rather than one at a time
