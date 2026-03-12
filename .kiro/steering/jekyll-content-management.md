# Jekyll Content Management Guide

## Overview

This guide provides comprehensive instructions for managing content on the Jekyll-based academic website (mdkar.github.io). It enables AI assistants to add, update, and manage website content including projects, publications, teaching materials, talks, music/art entries, and media assets while maintaining Jekyll conventions and consistent formatting.

The site uses the Academic Pages theme for Jekyll and follows specific conventions for content organization, frontmatter structure, and media embedding.

## Site Structure

### Collections

The Jekyll site uses the following collection directories:

- **`_projects/`** - Project portfolio entries (ACTIVELY USED)
- **`_musicart/`** - Music and art creative works (ACTIVELY USED)
- **`_publications/`** - Academic publications (contains dummy examples only)
- **`_teaching/`** - Teaching experience entries (contains dummy examples only)
- **`_talks/`** - Conference talks and presentations (contains dummy examples only)

**Note:** Most collections contain only dummy examples and are not actively used. The primary collections in use are `_projects/` and `_musicart/`.

### Key Directories

- **`/images/`** - All media assets (images, graphics) are stored here
- **`_config.yml`** - Site configuration file containing author information, social links, and Jekyll settings
- **`_pages/`** - Static pages for the website
- **`_data/`** - Data files for site content

### Configuration

The site configuration is managed through `_config.yml`, which includes:
- Site metadata (title, description, URL)
- Author profile information (name, bio, location, avatar)
- Social media links (GitHub, LinkedIn, Facebook, Instagram, YouTube, Discord)
- Collection definitions and permalink structures
- Jekyll build settings and plugins

## Glossary

**Content Manager** - The system that processes user content requests and generates properly formatted Jekyll files

**Jekyll Site** - The static website built using Jekyll and the Academic Pages theme

**Collection** - A Jekyll content type (projects, publications, teaching, talks, musicart) that groups related content

**Frontmatter** - YAML metadata at the beginning of markdown files enclosed in triple dashes (`---`), containing fields like title, date, permalink, and collection

**Permalink** - The URL path where a page will be accessible on the website (format: `/{collection}/{slug}/`)

**Asset** - Media files such as images, videos, or documents stored in the `/images/` directory

**Content File** - A markdown file with YAML frontmatter stored in a collection directory (e.g., `_projects/fairjams.md`)

**Validation Rules** - Format requirements for frontmatter fields, permalinks, dates, and filenames that ensure Jekyll can build the site correctly

**Kebab-Case** - A naming convention where words are lowercase and separated by hyphens (e.g., `my-project-name`)

**Excerpt** - A short summary or preview of content that appears in collection listings; can include HTML for thumbnail images

**Slug** - The URL-friendly version of a title used in permalinks (typically kebab-case)

**YAML** - A human-readable data serialization format used for frontmatter configuration

**Markdown Body** - The content section of a markdown file that appears after the closing `---` of the frontmatter



## Collection Types and Templates

This section provides detailed documentation for each actively used collection type, including required and optional frontmatter fields, body content structure, and example file references.

### Projects Collection (`_projects/`)

The projects collection showcases portfolio work, hackathon projects, software applications, and technical achievements. This is one of the primary actively used collections on the site.

#### Required Frontmatter Fields

- **`title`** (String) - The project name or title
  - Example: `"FairJams"`, `"GPAssist"`
  
- **`collection`** (String) - Must be `"projects"` (matches directory name)
  
- **`permalink`** (String) - URL path in format `/projects/{kebab-case-slug}/`
  - Example: `/projects/FairJams/`, `/projects/GPAssist/`
  - The slug should be derived from the title in kebab-case format
  
- **`date`** (String) - Project completion or publication date
  - Format: `YYYY-M-D` or `YYYY-MM-DD`
  - Examples: `2021-10-2`, `2019-4-12`, `2021-10-02`

#### Optional Frontmatter Fields

- **`excerpt`** (String) - Short project description for collection listings
  - Can include HTML for embedding thumbnail images
  - Format for image in excerpt: `<br/><br/><img src='/images/{filename}' style='height: 300px'>`
  - Example: `"FairJams is an app that creates provably fair music playlists...<br/><br/><img src='/images/FairJams.png' style='height: 300px'>"`

#### Body Content Structure

The markdown body (after the closing `---`) should include:

1. **Project Description** - Detailed explanation of what the project does and its purpose
   - Provide context about the project's goals and functionality
   - Explain the problem it solves or the need it addresses

2. **Development Context** (if applicable)
   - Hackathon or competition information
   - Development timeline and team composition
   - Your specific role and contributions
   - Example: "This project was made in less than 24 hours as part of the HackCMU 2021 hackathon."

3. **Awards and Recognition** (if applicable)
   - List any awards, honors, or recognition received
   - Include competition results or rankings
   - Format as a bold "Awards" section with bullet points
   - Example: `**Awards**\n* Honorable Mention - Code Fest (2019) at CHS`

4. **Technical Details** (if applicable)
   - Technology stack and frameworks used
   - Architecture or implementation approach
   - Key technical challenges and solutions
   - Example: "I worked on mainly the front end (using React-Native) and integration with Spotify"

5. **Links to Resources**
   - Code repositories (GitHub, GitLab, etc.)
   - Live demos or deployed applications
   - Project documentation or writeups
   - Format as markdown links: `[link text](url)`
   - Example: `[here](https://devpost.com/software/fairjams)`, `[Github](https://gist.github.com/...)`

6. **Media Embeds** (if applicable)
   - Images showing the project interface or results
   - YouTube video demonstrations or walkthroughs
   - Screenshots or diagrams

7. **Usage Instructions** (if applicable)
   - Installation steps
   - Configuration requirements
   - How to use the project
   - Format as numbered or bulleted lists with bold step labels
   - Example: `**Step 0:** install the [Tampermonkey](url) extension...`

#### Formatting Guidelines

- Use two newlines to separate major sections
- Use markdown headers (`##`, `###`) sparingly; bold text (`**Section Name**`) is preferred for subsections
- Format links as `[descriptive text](url)`
- Embed images using: `<img src='/images/{filename}' style='height: 300px'>`
- Embed YouTube videos using iframe (see Media Asset Handling section)
- Use bullet points (`*` or `-`) for lists
- Use numbered lists for sequential steps

#### Example Files

Reference these existing project files for formatting patterns:

- #[[file:_projects/FairJams.md]] - Project with image in excerpt, video embed, and external links
- #[[file:_projects/GPAssist.md]] - Project with awards section, detailed instructions, and video embed

### Music/Art Collection (`_musicart/`)

The musicart collection showcases creative works including music compositions, performances, art projects, and involvement in artistic organizations. This is one of the primary actively used collections on the site.

#### Required Frontmatter Fields

- **`title`** (String) - The creative work title or organization name
  - Example: `"CMU Anvil - A Maker Club"`
  
- **`collection`** (String) - Must be `"musicart"` (matches directory name)
  
- **`permalink`** (String) - URL path in format `/musicart/{kebab-case-slug}/`
  - Example: `/musicart/anvil/`
  - The slug should be derived from the title in kebab-case format
  
- **`date`** (String) - Creation date or involvement start date
  - Format: `YYYY-M-D` or `YYYY-MM-DD`
  - Example: `2021-8-1`

#### Optional Frontmatter Fields

- **`excerpt`** (String) - Short description or role for collection listings
  - Can include HTML for embedding thumbnail images
  - Format for image in excerpt: `<img src='/images/{filename}'>`
  - Example: `"President<br/><br/><img src='/images/Anvil.png'>"`

#### Body Content Structure

The markdown body (after the closing `---`) should include:

1. **Role or Position** (if applicable)
   - Your involvement with the organization or project
   - Leadership positions held
   - Timeline of involvement
   - Example: "Education Co-Chair for S21 and F21 semesters."

2. **Contributions and Activities**
   - Specific work you did or are doing
   - Workshops, events, or performances organized or participated in
   - Creative output or achievements
   - Example: "I create, plan, and lead workshops throughout the semester."

3. **Impact and Accomplishments**
   - Notable achievements or milestones
   - Awards or recognition received
   - Growth or improvements made
   - Example: "I won $100 for the club's budget in the virtual Booth competition during Carnival."

4. **Media and Links**
   - Links to organization websites, portfolios, or recordings
   - Embedded images of artwork or performances
   - Videos of performances or demonstrations
   - Format as markdown links or HTML embeds

5. **Context and Background** (if applicable)
   - Description of the organization or project
   - Mission or purpose
   - Collaboration details

#### Formatting Guidelines

- Keep entries concise and focused on your role and contributions
- Use paragraph breaks (two newlines) to separate distinct topics
- Format external links as markdown: `[descriptive text](url)` or raw URLs with angle brackets: `<url>`
- Embed images using: `<img src='/images/{filename}'>`
- Use bullet points for lists of activities or achievements
- Avoid excessive headers; use natural paragraph flow

#### Example Files

Reference this existing musicart file for formatting patterns:

- #[[file:_musicart/anvil.md]] - Organization involvement with role description and external link



## Validation Rules

This section defines the format requirements and validation rules that must be followed when creating or updating Jekyll content files. These rules ensure that Jekyll can successfully parse and build the site.

### Frontmatter Validation

#### Triple Dash Enclosure (Requirement 8.1)

All frontmatter must be enclosed by triple dashes (`---`) on separate lines at the beginning and end of the frontmatter block.

**Valid format:**
```yaml
---
title: "My Project"
collection: projects
permalink: /projects/my-project/
date: 2024-1-15
---
```

**Invalid formats:**
```yaml
# Missing opening dashes
title: "My Project"
collection: projects
---

# Missing closing dashes
---
title: "My Project"
collection: projects

# Wrong delimiter
***
title: "My Project"
***
```

**Validation check:** Verify that the file starts with `---` on the first line and has a matching `---` before the markdown body begins.

#### YAML Syntax Validity

Frontmatter must be valid YAML that can be parsed without syntax errors.

**Common YAML syntax rules:**
- Use `key: value` format with a space after the colon
- Enclose string values containing special characters in quotes
- Use consistent indentation (spaces, not tabs)
- Escape quotes within quoted strings or use alternate quote style
- Multi-line strings can use `|` (literal) or `>` (folded) indicators

**Valid YAML:**
```yaml
---
title: "Project: A New Hope"
excerpt: "This is a description with \"quotes\" inside"
collection: projects
---
```

**Invalid YAML:**
```yaml
---
title: Project: A New Hope  # Unquoted string with colon
excerpt: "Unclosed quote
collection:projects  # Missing space after colon
---
```

**Validation check:** Parse the frontmatter as YAML and catch any syntax errors. Report the specific line and error type.

#### Required Fields (Requirement 8.6)

Every content file must include these required frontmatter fields:

- **`title`** - Must be present and non-empty
- **`collection`** - Must be present and match the directory name
- **`permalink`** - Must be present and follow the correct format
- **`date`** - Must be present and follow a valid date format

**Validation check:** After parsing frontmatter, verify that all four required fields exist and have non-empty values.

**Error reporting:** If any required field is missing, report all missing fields at once:
```
Missing required frontmatter fields: title, date
```

#### Collection Field Consistency (Requirement 8.2)

The `collection` field value must match the directory name where the file is located.

**Directory-to-collection mapping:**
- Files in `_projects/` must have `collection: projects`
- Files in `_musicart/` must have `collection: musicart`
- Files in `_publications/` must have `collection: publications`
- Files in `_teaching/` must have `collection: teaching`
- Files in `_talks/` must have `collection: talks`

**Valid example:**
```
File: _projects/my-project.md
Frontmatter: collection: projects  ✓
```

**Invalid example:**
```
File: _projects/my-project.md
Frontmatter: collection: musicart  ✗
```

**Validation check:** Extract the directory name from the file path and verify it matches the collection field value.

**Error reporting:**
```
Collection field mismatch: file is in _projects/ but collection field is "musicart"
Expected: collection: projects
```

### Permalink Format Validation (Requirement 8.3)

Permalinks must follow the format `/{collection}/{slug}/` where:
- The collection matches the content type
- The slug is in kebab-case format
- The permalink starts with `/` and ends with `/`

**Valid permalink formats:**
```yaml
permalink: /projects/fairjams/
permalink: /projects/my-awesome-project/
permalink: /musicart/anvil/
permalink: /musicart/piano-composition-2024/
```

**Invalid permalink formats:**
```yaml
permalink: projects/fairjams/        # Missing leading slash
permalink: /projects/fairjams        # Missing trailing slash
permalink: /projects/FairJams/       # Not kebab-case (uppercase)
permalink: /projects/fair_jams/      # Not kebab-case (underscore)
permalink: /projects/fair jams/      # Not kebab-case (space)
permalink: /musicart/my-project/     # Wrong collection
```

**Validation rules:**
1. Must start with `/`
2. Must end with `/`
3. Must have format `/{collection}/{slug}/`
4. Collection part must match the file's collection
5. Slug must be kebab-case: lowercase letters, numbers, and hyphens only
6. Slug cannot start or end with a hyphen
7. Slug cannot have consecutive hyphens

**Validation check:** Use a regular expression to validate the permalink format:
```
^/[a-z]+/[a-z0-9]+(-[a-z0-9]+)*/$
```

**Error reporting:**
```
Invalid permalink format: /projects/FairJams/
Expected format: /{collection}/{kebab-case-slug}/
Suggestion: /projects/fairjams/
```

### Date Format Validation (Requirement 8.4)

Dates must follow one of two accepted formats and represent valid calendar dates.

**Accepted formats:**
1. `YYYY-M-D` - Year with 4 digits, month and day with 1-2 digits
   - Examples: `2021-10-2`, `2019-4-12`, `2024-1-5`
2. `YYYY-MM-DD` - Year with 4 digits, month and day with 2 digits (zero-padded)
   - Examples: `2021-10-02`, `2019-04-12`, `2024-01-05`

**Valid dates:**
```yaml
date: 2021-10-2
date: 2021-10-02
date: 2019-4-12
date: 2024-1-5
```

**Invalid dates:**
```yaml
date: 21-10-2          # Year must be 4 digits
date: 2021/10/02       # Wrong separator (use hyphen)
date: 2021-13-01       # Invalid month (13)
date: 2021-02-30       # Invalid day for February
date: 10-2-2021        # Wrong order (must be YYYY-M-D)
date: October 2, 2021  # Must be numeric format
```

**Validation rules:**
1. Format must match `YYYY-M-D` or `YYYY-MM-DD` pattern
2. Year must be between 1900 and 2100 (reasonable range)
3. Month must be between 1 and 12
4. Day must be valid for the given month and year (account for leap years)

**Validation check:** Parse the date string and verify it represents a valid calendar date.

**Error reporting:**
```
Invalid date format: 2021/10/02
Expected format: YYYY-M-D or YYYY-MM-DD
Example: 2021-10-2 or 2021-10-02
```

### Filename Validation

#### Kebab-Case Format (Requirements 10.1, 10.2, 10.4, 10.5)

Content filenames must follow kebab-case naming conventions.

**Kebab-case rules:**
1. All lowercase letters
2. Words separated by hyphens (`-`)
3. No special characters except hyphens
4. No spaces
5. Numbers are allowed
6. Must end with `.md` extension
7. Cannot start or end with a hyphen
8. Cannot have consecutive hyphens

**Valid filenames:**
```
fairjams.md
gpassist.md
my-awesome-project.md
project-2024.md
anvil.md
piano-composition-1.md
```

**Invalid filenames:**
```
FairJams.md           # Uppercase letters
fair_jams.md          # Underscore instead of hyphen
fair jams.md          # Space instead of hyphen
my-project!.md        # Special character (!)
-my-project.md        # Starts with hyphen
my-project-.md        # Ends with hyphen (before extension)
my--project.md        # Consecutive hyphens
myproject.txt         # Wrong extension
```

**Filename generation from title:**

When generating a filename from a title, apply these transformations:
1. Convert to lowercase
2. Replace spaces with hyphens
3. Remove all special characters except hyphens and alphanumeric characters
4. Replace multiple consecutive hyphens with a single hyphen
5. Remove leading and trailing hyphens
6. Append `.md` extension

**Examples:**
```
"FairJams" → fairjams.md
"My Awesome Project!" → my-awesome-project.md
"GPAssist: Grade Calculator" → gpassist-grade-calculator.md
"Project #1 (2024)" → project-1-2024.md
```

**Validation check:** Use a regular expression to validate filename format:
```
^[a-z0-9]+(-[a-z0-9]+)*\.md$
```

#### Markdown Extension (Requirement 10.2)

All content files must use the `.md` file extension.

**Valid:** `project.md`, `my-project.md`

**Invalid:** `project.txt`, `project.markdown`, `project.html`

**Validation check:** Verify the filename ends with `.md`

#### Filename Uniqueness (Requirement 10.3)

Filenames must be unique within their collection directory. If a filename collision is detected, append a numeric suffix.

**Collision handling:**
1. Check if the generated filename already exists in the target directory
2. If it exists, append `-2` before the `.md` extension
3. If that exists, try `-3`, `-4`, etc. until a unique filename is found
4. Inform the user of the modified filename

**Example collision resolution:**
```
Desired: my-project.md (already exists)
Try: my-project-2.md (already exists)
Try: my-project-3.md (available)
Use: my-project-3.md
```

**User notification:**
```
Note: Filename "my-project.md" already exists.
Using "my-project-3.md" instead.
```

**Validation check:** Before writing a file, check if the filename exists in the target collection directory. If it does, generate an alternative filename with a numeric suffix.

### Validation Workflow

When creating or updating content, perform validations in this order:

1. **Filename validation** - Ensure the filename follows kebab-case rules and has `.md` extension
2. **Filename uniqueness** - Check for collisions and resolve if necessary
3. **Frontmatter structure** - Verify triple dash enclosure
4. **YAML syntax** - Parse frontmatter and catch syntax errors
5. **Required fields** - Verify all required fields are present and non-empty
6. **Collection consistency** - Verify collection field matches directory
7. **Permalink format** - Validate permalink follows the correct pattern
8. **Date format** - Validate date follows accepted formats and is a valid date

**Error handling strategy:**
- Collect all validation errors before reporting (don't stop at the first error)
- Report errors in a clear, actionable format
- Provide specific examples of correct formats
- Suggest corrections when possible
- Do not write the file if any validation errors are detected

**Example comprehensive error report:**
```
Validation errors found:

1. Filename format: "My Project.md"
   Issue: Contains uppercase letters and spaces
   Expected: Kebab-case format (lowercase with hyphens)
   Suggestion: my-project.md

2. Missing required fields: date
   Add: date: YYYY-M-D (e.g., 2024-3-12)

3. Invalid permalink: /projects/My Project/
   Issue: Contains uppercase and spaces
   Expected: /projects/{kebab-case-slug}/
   Suggestion: /projects/my-project/

Please fix these issues before creating the file.
```



## Media Asset Handling

This section provides guidance on managing media assets (images and videos) for Jekyll content, including file placement, naming conventions, embedding templates, and efficient workflow tools.

### Image File Management

#### Image File Placement (Requirement 3.1)

All image files must be stored in the `/images/` directory at the root of the Jekyll site.

**Correct placement:**
```
/images/FairJams.png
/images/GPAssist.png
/images/Anvil.png
/images/my-project-screenshot.jpg
```

**Incorrect placement:**
```
_projects/images/FairJams.png        # Wrong: images in collection directory
/assets/FairJams.png                 # Wrong: images in assets directory
/img/FairJams.png                    # Wrong: wrong directory name
```

**Important notes:**
- The `/images/` directory is at the root level, not within collection directories
- All collections share the same `/images/` directory
- Image files are referenced in content using the path `/images/{filename}`

#### Image Filename Conventions (Requirement 3.2)

Image filenames should follow kebab-case naming conventions and use descriptive names that indicate the content or purpose.

**Kebab-case rules for image filenames:**
1. All lowercase letters
2. Words separated by hyphens (`-`)
3. No special characters except hyphens
4. No spaces
5. Numbers are allowed
6. Use descriptive names that relate to the content

**Supported image formats (Requirement 3.6):**
- `.jpg` / `.jpeg` - JPEG images
- `.png` - PNG images (preferred for screenshots and graphics with transparency)
- `.gif` - GIF images (for animations)
- `.svg` - SVG vector graphics

**Valid image filenames:**
```
fairjams.png
gpassist-screenshot.jpg
anvil-logo.png
project-demo-2024.jpg
user-interface-mockup.png
architecture-diagram.svg
animation-demo.gif
```

**Invalid image filenames:**
```
FairJams.png              # Uppercase letters
fair_jams.png             # Underscore instead of hyphen
fair jams.png             # Spaces
my-project!.png           # Special character
MyProject Screenshot.jpg  # Uppercase and spaces
image1.png                # Not descriptive enough
```

**Filename generation guidelines:**
- Use project or content names in the filename for easy identification
- Add descriptive suffixes like `-screenshot`, `-logo`, `-diagram`, `-mockup`
- Keep filenames concise but meaningful
- Avoid generic names like `image1.png` or `photo.jpg`

### Image Embedding

#### Standard Image Embed Template (Requirement 3.3)

Images are embedded in markdown content using HTML `<img>` tags with inline styles for sizing.

**Standard template (default 300px height):**
```html
<img src='/images/{filename}' style='height: 300px'>
```

**Examples:**
```html
<img src='/images/FairJams.png' style='height: 300px'>
<img src='/images/project-screenshot.jpg' style='height: 300px'>
<img src='/images/anvil-logo.png' style='height: 300px'>
```

**Usage contexts:**

1. **In excerpt field (for thumbnails):**
```yaml
excerpt: "Project description text<br/><br/><img src='/images/FairJams.png' style='height: 300px'>"
```
Note: Use `<br/><br/>` before the image to add spacing between text and image

2. **In markdown body:**
```markdown
---
frontmatter here
---

Project description paragraph.

<img src='/images/project-demo.png' style='height: 300px'>

More content here.
```

#### Custom Image Dimensions (Requirement 3.5)

You can customize image dimensions by modifying the `style` attribute.

**Custom height:**
```html
<img src='/images/large-diagram.png' style='height: 500px'>
<img src='/images/small-icon.png' style='height: 150px'>
```

**Custom width:**
```html
<img src='/images/wide-screenshot.png' style='width: 800px'>
<img src='/images/narrow-sidebar.png' style='width: 400px'>
```

**Both width and height:**
```html
<img src='/images/fixed-size.png' style='width: 600px; height: 400px'>
```

**Responsive sizing (percentage-based):**
```html
<img src='/images/full-width.png' style='width: 100%'>
<img src='/images/half-width.png' style='width: 50%'>
```

**Additional style properties:**
```html
<!-- Centered image -->
<img src='/images/centered.png' style='height: 300px; display: block; margin: 0 auto'>

<!-- Image with border -->
<img src='/images/bordered.png' style='height: 300px; border: 1px solid #ccc'>

<!-- Image with shadow -->
<img src='/images/shadow.png' style='height: 300px; box-shadow: 0 4px 6px rgba(0,0,0,0.1)'>
```

**Best practices:**
- Default to `height: 300px` for consistency unless there's a specific reason to change it
- Use height-based sizing to maintain aspect ratio (width adjusts automatically)
- For very wide images (screenshots, diagrams), consider using width-based sizing
- Test image sizing on different screen sizes if possible

### Video Embedding

#### YouTube Video Embed Template (Requirement 3.4)

YouTube videos are embedded using HTML `<iframe>` elements with standard dimensions and attributes.

**Standard YouTube embed template:**
```html
<iframe width="768" height="543" src="https://www.youtube.com/embed/{video-id}" 
        title="YouTube video player" frameborder="0" 
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
        allowfullscreen></iframe>
```

**How to get the video ID:**
- From URL `https://www.youtube.com/watch?v=KNwLJif7N14`, the video ID is `KNwLJif7N14`
- From URL `https://youtu.be/KNwLJif7N14`, the video ID is `KNwLJif7N14`
- The video ID is the alphanumeric string after `v=` or after `youtu.be/`

**Complete example:**
```html
<iframe width="768" height="543" src="https://www.youtube.com/embed/KNwLJif7N14" 
        title="YouTube video player" frameborder="0" 
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
        allowfullscreen></iframe>
```

**Alternative dimensions:**

While the standard is `width="768" height="543"`, you may encounter or use these alternatives:

```html
<!-- Smaller embed (16:9 ratio) -->
<iframe width="560" height="315" src="https://www.youtube.com/embed/{video-id}" 
        title="YouTube video player" frameborder="0" 
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
        allowfullscreen></iframe>

<!-- Larger embed (16:9 ratio) -->
<iframe width="960" height="540" src="https://www.youtube.com/embed/{video-id}" 
        title="YouTube video player" frameborder="0" 
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
        allowfullscreen></iframe>
```

**Placement in content:**
```markdown
---
frontmatter here
---

Project description paragraph.

Here's a video demonstration:

<iframe width="768" height="543" src="https://www.youtube.com/embed/KNwLJif7N14" 
        title="YouTube video player" frameborder="0" 
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
        allowfullscreen></iframe>

More content after the video.
```

**Best practices:**
- Use the standard dimensions (`768x543` or `560x315`) for consistency
- Maintain the 16:9 aspect ratio when using custom dimensions
- Always include the `allowfullscreen` attribute
- Keep the `allow` attribute with all permissions for full functionality
- Add a blank line before and after the iframe for proper markdown rendering

### Efficient Media Workflow Tools

To streamline the process of getting media files into the `/images/` directory, use command-line tools for copying, downloading, resizing, and converting images.

#### Copying Local Files

**Using `cp` command:**
```bash
# Copy a single image
cp ~/Downloads/screenshot.png images/project-screenshot.png

# Copy multiple images
cp ~/Downloads/image1.png ~/Downloads/image2.jpg images/

# Copy and rename
cp ~/path/to/original.png images/descriptive-name.png
```

**Using `mv` command (move instead of copy):**
```bash
# Move image from Downloads
mv ~/Downloads/screenshot.png images/project-screenshot.png
```

#### Downloading Images from URLs

**Using `curl`:**
```bash
# Download image with custom filename
curl -o images/project-logo.png https://example.com/logo.png

# Download image keeping original filename
curl -O --output-dir images/ https://example.com/image.png
```

**Using `wget`:**
```bash
# Download to images directory
wget -P images/ https://example.com/screenshot.png

# Download with custom filename
wget -O images/custom-name.png https://example.com/image.png
```

#### Resizing Images

**Using ImageMagick (`convert` command):**
```bash
# Install ImageMagick first (if not installed)
# macOS: brew install imagemagick
# Linux: apt-get install imagemagick

# Resize to specific width (maintains aspect ratio)
convert input.png -resize 800x images/resized-image.png

# Resize to specific height (maintains aspect ratio)
convert input.png -resize x600 images/resized-image.png

# Resize to fit within dimensions (maintains aspect ratio)
convert input.png -resize 800x600 images/resized-image.png

# Resize to exact dimensions (may distort)
convert input.png -resize 800x600! images/resized-image.png
```

**Using `sips` (macOS built-in):**
```bash
# Resize to specific width
sips -Z 800 input.png --out images/resized-image.png

# Resize to specific height
sips -z 600 0 input.png --out images/resized-image.png

# Resize to specific dimensions
sips -z 600 800 input.png --out images/resized-image.png
```

#### Converting Image Formats

**Using ImageMagick:**
```bash
# Convert PNG to JPG
convert input.png images/output.jpg

# Convert JPG to PNG
convert input.jpg images/output.png

# Convert with quality setting (for JPG)
convert input.png -quality 85 images/output.jpg

# Convert and resize in one command
convert input.png -resize 800x -quality 85 images/optimized.jpg
```

**Using `sips` (macOS):**
```bash
# Convert to JPEG
sips -s format jpeg input.png --out images/output.jpg

# Convert to PNG
sips -s format png input.jpg --out images/output.png
```

#### Batch Processing Multiple Images

**Resize all images in a directory:**
```bash
# Using ImageMagick
for img in ~/Downloads/*.png; do
  filename=$(basename "$img" .png)
  convert "$img" -resize 800x "images/${filename}.png"
done

# Using sips (macOS)
for img in ~/Downloads/*.png; do
  filename=$(basename "$img")
  sips -Z 800 "$img" --out "images/${filename}"
done
```

**Convert all PNG to JPG:**
```bash
for img in ~/Downloads/*.png; do
  filename=$(basename "$img" .png)
  convert "$img" -quality 85 "images/${filename}.jpg"
done
```

#### Optimizing Image File Sizes

**Using ImageMagick:**
```bash
# Compress PNG
convert input.png -strip -quality 85 images/optimized.png

# Compress JPG
convert input.jpg -strip -quality 80 images/optimized.jpg
```

**Using `optipng` (for PNG files):**
```bash
# Install: brew install optipng (macOS) or apt-get install optipng (Linux)
optipng -o5 images/image.png
```

**Using `jpegoptim` (for JPG files):**
```bash
# Install: brew install jpegoptim (macOS) or apt-get install jpegoptim (Linux)
jpegoptim --max=85 images/image.jpg
```

#### Complete Workflow Examples

**Example 1: Download, resize, and optimize an image:**
```bash
# Download image
curl -o temp-image.png https://example.com/large-image.png

# Resize and optimize
convert temp-image.png -resize 800x -strip -quality 85 images/project-screenshot.png

# Remove temporary file
rm temp-image.png
```

**Example 2: Copy, rename, and resize a local screenshot:**
```bash
# Copy and resize in one command
convert ~/Desktop/screenshot.png -resize 1200x images/project-demo.png
```

**Example 3: Batch process multiple images from Downloads:**
```bash
# Resize and move all screenshots
for img in ~/Downloads/Screenshot*.png; do
  filename=$(basename "$img" | tr '[:upper:]' '[:lower:]' | tr ' ' '-')
  convert "$img" -resize 1000x -strip images/"$filename"
done
```

#### Quick Reference Commands

```bash
# Copy image
cp source.png images/destination.png

# Download image
curl -o images/filename.png https://example.com/image.png

# Resize image (ImageMagick)
convert input.png -resize 800x images/output.png

# Resize image (sips, macOS)
sips -Z 800 input.png --out images/output.png

# Convert format
convert input.png images/output.jpg

# Optimize PNG
optipng -o5 images/image.png

# Optimize JPG
jpegoptim --max=85 images/image.jpg
```

**Best practices for media workflow:**
- Use descriptive, kebab-case filenames when copying or downloading
- Resize large images before adding them to the repository to reduce file size
- Optimize images to improve website load times
- Keep original high-resolution images in a separate location if needed
- Use batch processing for multiple images to save time
- Verify images are in the correct `/images/` directory after processing



## Common Workflows

This section provides step-by-step guides for common content management tasks. Each workflow includes validation checkpoints to ensure content meets Jekyll requirements.

### Creating New Content

This workflow guides you through adding new projects or musicart entries to the website.

#### Adding a New Project

Follow these steps to create a new project entry:

**Step 1: Gather Required Information**

Collect all required information before starting:
- Project title (required)
- Project description (required)
- Completion or publication date (required)
- Optional: excerpt/summary, awards, technical details, links, media

**Validation checkpoint:** Verify you have at minimum: title, description, and date.

**Step 2: Generate Filename**

Convert the project title to a kebab-case filename:
1. Convert title to lowercase
2. Replace spaces with hyphens
3. Remove special characters (keep only letters, numbers, hyphens)
4. Append `.md` extension

Examples:
- "FairJams" → `fairjams.md`
- "My Awesome Project!" → `my-awesome-project.md`
- "GPAssist: Grade Calculator" → `gpassist-grade-calculator.md`

**Validation checkpoint:** Filename must match pattern `^[a-z0-9]+(-[a-z0-9]+)*\.md$`

**Step 3: Check for Filename Collisions**

Check if the filename already exists in `_projects/` directory:
- If unique: proceed with the filename
- If exists: append `-2`, `-3`, etc. until unique
- Inform user of any filename changes

**Step 4: Create Frontmatter**

Build the YAML frontmatter with required and optional fields:

```yaml
---
title: "Project Title"
collection: projects
permalink: /projects/{kebab-case-slug}/
date: YYYY-M-D
excerpt: "Optional short description<br/><br/><img src='/images/{filename}' style='height: 300px'>"
---
```

**Frontmatter rules:**
- Enclose in triple dashes (`---`)
- `collection` must be `"projects"`
- `permalink` format: `/projects/{slug}/` where slug is kebab-case
- `date` format: `YYYY-M-D` or `YYYY-MM-DD`
- `excerpt` is optional but recommended; can include HTML image embed

**Validation checkpoint:** 
- All required fields present (title, collection, permalink, date)
- Collection field is "projects"
- Permalink matches `/projects/[a-z0-9-]+/` pattern
- Date matches `YYYY-M-D` or `YYYY-MM-DD` format

**Step 5: Format Body Content**

Structure the markdown body with these sections (as applicable):

1. **Project Description** - What the project does and its purpose
2. **Development Context** - Hackathon info, timeline, team, your role
3. **Awards** (if applicable) - Format as `**Awards**` with bullet points
4. **Technical Details** - Tech stack, architecture, challenges
5. **Links** - Code repos, demos, documentation as markdown links
6. **Media** - Images and videos embedded with proper HTML
7. **Instructions** (if applicable) - Installation and usage steps

**Formatting guidelines:**
- Separate sections with two newlines (blank line)
- Use bold text for section headers: `**Section Name**`
- Format links: `[descriptive text](url)`
- Embed images: `<img src='/images/{filename}' style='height: 300px'>`
- Embed videos: Use iframe template (see Media Asset Handling section)

**Step 6: Handle Media Assets**

If the project includes images or videos:

**For images:**
1. Place image files in `/images/` directory
2. Use kebab-case filenames: `project-name-screenshot.png`
3. Supported formats: jpg, jpeg, png, gif, svg
4. Embed in content: `<img src='/images/{filename}' style='height: 300px'>`

**For YouTube videos:**
1. Extract video ID from URL (after `v=` or `youtu.be/`)
2. Use iframe template with standard dimensions (768x543 or 560x315)
3. Include all required attributes (allowfullscreen, allow, etc.)

**Validation checkpoint:**
- All image files exist in `/images/` directory
- Image filenames follow kebab-case convention
- Image embeds use correct HTML format
- Video embeds use proper iframe template

**Step 7: Validate Complete File**

Before writing the file, perform final validation:

1. **Filename** - Kebab-case, `.md` extension, unique in `_projects/`
2. **Frontmatter structure** - Enclosed in `---`, valid YAML syntax
3. **Required fields** - title, collection, permalink, date all present
4. **Collection consistency** - `collection: projects` matches directory
5. **Permalink format** - `/projects/{kebab-case-slug}/`
6. **Date format** - Valid `YYYY-M-D` or `YYYY-MM-DD` date
7. **Body content** - Well-formatted markdown with proper sections

**If validation fails:** Report all errors at once with specific corrections needed. Do not write the file until all issues are resolved.

**Step 8: Write File**

Write the complete content to `_projects/{filename}.md`:
- Frontmatter with triple dashes
- Blank line after closing `---`
- Markdown body content

**Step 9: Confirm Creation**

Inform the user of successful creation:
- File path: `_projects/{filename}.md`
- Permalink where it will be accessible: `/projects/{slug}/`
- Any filename modifications made (if collision occurred)
- Summary of content added

**Example complete project file:**

```markdown
---
title: "FairJams"
excerpt: "FairJams is an app that creates provably fair music playlists.<br/><br/><img src='/images/FairJams.png' style='height: 300px'>"
collection: projects
permalink: /projects/fairjams/
date: 2021-10-2
---

FairJams is an app that creates provably fair music playlists. It connects with Spotify and uses fast and innovative algorithms from fair division research.

This project was made in less than 24 hours as part of the HackCMU 2021 hackathon. I worked on mainly the front end (using React-Native) and integration with Spotify, while my partner worked on the back end.

The details of the project along with links to the code can be found [here](https://devpost.com/software/fairjams)

<iframe width="768" height="543" src="https://www.youtube.com/embed/KNwLJif7N14" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
```

#### Adding a New Music/Art Entry

Follow these steps to create a new musicart entry:

**Step 1: Gather Required Information**

Collect all required information:
- Title of work or organization name (required)
- Description of your role or the work (required)
- Date (creation date or involvement start date) (required)
- Optional: excerpt, images, links to websites or recordings

**Validation checkpoint:** Verify you have title, description, and date.

**Step 2: Generate Filename**

Convert the title to a kebab-case filename following the same rules as projects:
- Lowercase, spaces to hyphens, remove special characters, append `.md`

Examples:
- "CMU Anvil - A Maker Club" → `cmu-anvil-a-maker-club.md`
- "Piano Composition #1" → `piano-composition-1.md`

**Validation checkpoint:** Filename matches `^[a-z0-9]+(-[a-z0-9]+)*\.md$`

**Step 3: Check for Filename Collisions**

Check if filename exists in `_musicart/` directory and resolve collisions if needed.

**Step 4: Create Frontmatter**

Build the YAML frontmatter:

```yaml
---
title: "Work or Organization Title"
collection: musicart
permalink: /musicart/{kebab-case-slug}/
date: YYYY-M-D
excerpt: "Optional role or description<br/><br/><img src='/images/{filename}'>"
---
```

**Frontmatter rules:**
- `collection` must be `"musicart"`
- `permalink` format: `/musicart/{slug}/`
- Date and excerpt follow same rules as projects

**Validation checkpoint:**
- All required fields present
- Collection field is "musicart"
- Permalink matches `/musicart/[a-z0-9-]+/` pattern
- Date is valid

**Step 5: Format Body Content**

Structure the body with these sections (as applicable):

1. **Role or Position** - Your involvement, leadership positions, timeline
2. **Contributions** - Specific work, workshops, events, performances
3. **Impact** - Achievements, awards, growth, improvements
4. **Media and Links** - Organization websites, portfolios, recordings
5. **Context** - Description of organization or project, mission, collaborations

**Formatting guidelines:**
- Keep entries concise and focused
- Use paragraph breaks (two newlines) between topics
- Format links as markdown or raw URLs with angle brackets
- Embed images: `<img src='/images/{filename}'>`
- Natural paragraph flow (avoid excessive headers)

**Step 6: Handle Media Assets**

Follow same media handling process as projects:
- Images in `/images/` with kebab-case names
- Proper embed formats for images and videos

**Step 7: Validate Complete File**

Perform same validation checks as projects, but with `collection: musicart` and `/musicart/` permalink.

**Step 8: Write File**

Write content to `_musicart/{filename}.md`

**Step 9: Confirm Creation**

Inform user of successful creation with file path and permalink.

**Example complete musicart file:**

```markdown
---
title: "CMU Anvil - A Maker Club"
excerpt: "President<br/><br/><img src='/images/Anvil.png'>"
collection: musicart
permalink: /musicart/anvil/
date: 2021-8-1
---

Education Co-Chair for S21 and F21 semesters. I create, plan, and lead workshops throughout the semester. 
During the pandemic I introduced the club to several new members and led 4 virtual workshops. Also, I won $100 for the club's budget in the virtual Booth competition during Carnival.

Anvil's website: <https://cmuanvil.wordpress.com/>
```

### Updating Existing Content

This workflow guides you through modifying existing projects, musicart entries, or other content files.

#### Locating Content by Title or Permalink

**Method 1: Search by Title**

1. Ask user for the title or partial title of the content
2. Search the appropriate collection directory (`_projects/`, `_musicart/`, etc.)
3. Look for files where the frontmatter `title` field matches (case-insensitive)
4. If multiple matches found, present list to user for selection
5. If no matches found, suggest similar titles or offer to create new content

**Search approach:**
```bash
# Search for title in frontmatter
grep -l "title:.*{search-term}" _projects/*.md
```

**Method 2: Search by Permalink**

1. Ask user for the permalink (e.g., `/projects/fairjams/`)
2. Extract collection and slug from permalink
3. Search files in that collection directory for matching permalink in frontmatter
4. Permalink should uniquely identify a single file

**Method 3: Search by Filename**

1. If user provides a filename, look directly in the collection directory
2. Check if file exists: `_projects/{filename}.md`

**Handling non-existent files:**

If the file is not found:
1. Inform user the file doesn't exist
2. Show similar filenames or titles if available
3. Offer to create a new file with the provided information
4. Ask user to confirm whether to create new or continue searching

**Example user interaction:**
```
User: "Update the FairJams project"
Assistant: Found: _projects/fairjams.md (title: "FairJams", permalink: /projects/FairJams/)
```

```
User: "Update the project at /projects/my-app/"
Assistant: File not found with permalink /projects/my-app/
Did you mean one of these?
- /projects/my-awesome-app/ (My Awesome App)
- /projects/myapp/ (MyApp)
Or would you like to create a new project?
```

#### Updating Frontmatter Fields

When updating frontmatter fields while preserving others:

**Step 1: Read Current Content**

1. Read the entire file content
2. Parse frontmatter and body separately
3. Extract all current frontmatter fields into a data structure

**Step 2: Identify Fields to Update**

1. Determine which fields the user wants to change
2. Keep all other fields unchanged
3. Validate new field values before applying

**Step 3: Apply Updates**

1. Modify only the specified fields
2. Preserve all other frontmatter fields exactly as they were
3. Maintain field order if possible (or use consistent ordering)
4. Keep the same YAML formatting style

**Step 4: Validate Updated Frontmatter**

Before writing changes:
- Verify YAML syntax is still valid
- Check required fields are still present
- Validate field values (date format, permalink format, etc.)
- Ensure collection field still matches directory

**Step 5: Write Updated File**

1. Write updated frontmatter with triple dashes
2. Keep body content unchanged (unless also updating body)
3. Preserve blank line after closing `---`

**Example frontmatter update:**

Original:
```yaml
---
title: "Old Title"
excerpt: "Old description"
collection: projects
permalink: /projects/old-title/
date: 2021-10-2
---
```

User requests: "Update title to 'New Title' and date to 2024-3-12"

Updated:
```yaml
---
title: "New Title"
excerpt: "Old description"
collection: projects
permalink: /projects/old-title/
date: 2024-3-12
---
```

Note: `excerpt`, `collection`, and `permalink` fields preserved unchanged.

#### Replacing vs Appending Body Content

When updating the markdown body, clarify with the user whether to replace or append:

**Replace Mode (default for major updates):**

1. Read current body content
2. Show user the current content (or summary)
3. Confirm they want to replace it entirely
4. Write new body content, discarding old content
5. Preserve frontmatter unchanged

**Use replace mode when:**
- Complete rewrite of project description
- Restructuring content organization
- User explicitly says "replace" or "rewrite"

**Append Mode (for adding new information):**

1. Read current body content
2. Add new content after existing content
3. Insert appropriate spacing (two newlines) between old and new
4. Preserve frontmatter unchanged

**Use append mode when:**
- Adding new sections (awards, links, updates)
- Adding media embeds to existing content
- User explicitly says "add" or "append"
- User wants to keep existing content

**Example replace:**

Original body:
```markdown
Old project description.
```

User: "Replace description with: New project description with more details."

Result:
```markdown
New project description with more details.
```

**Example append:**

Original body:
```markdown
Project description.

[Link to code](https://github.com/...)
```

User: "Add a video demonstration"

Result:
```markdown
Project description.

[Link to code](https://github.com/...)

<iframe width="768" height="543" src="https://www.youtube.com/embed/VIDEO_ID" 
        title="YouTube video player" frameborder="0" 
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
        allowfullscreen></iframe>
```

**Step-by-step body update process:**

1. **Clarify intent** - Ask user if they want to replace or append (if ambiguous)
2. **Read current content** - Load existing body content
3. **Prepare new content** - Format new content according to guidelines
4. **Apply update** - Replace or append based on mode
5. **Validate** - Ensure markdown is well-formed
6. **Write file** - Save updated content
7. **Confirm** - Inform user of changes made

#### Complete Update Workflow

**Step 1: Locate the file** (using title, permalink, or filename)

**Step 2: Read current content** (parse frontmatter and body)

**Step 3: Determine update scope**
- Frontmatter only?
- Body only?
- Both?

**Step 4: Apply updates**
- For frontmatter: preserve unchanged fields
- For body: replace or append based on user intent

**Step 5: Validate updated content**
- Check YAML syntax
- Verify required fields
- Validate field formats
- Check markdown structure

**Step 6: Write updated file**

**Step 7: Confirm changes**
- Summarize what was updated
- Show before/after for changed fields (if helpful)
- Confirm file location

**Example complete update:**

```
User: "Update the FairJams project to add an award"


Assistant actions:
1. Locate: _projects/fairjams.md
2. Read current content
3. Determine: Body update (append mode)
4. Format award section:
   **Awards**
   * Best Fair Division Algorithm - HackCMU 2021
5. Append to body after existing description
6. Validate: Markdown structure correct
7. Write updated file
8. Confirm: "Added awards section to FairJams project"
```

### Managing Media Assets

This workflow guides you through adding images and videos to content.

#### Adding Images to Projects

Follow these steps to add images to a project or musicart entry:

**Step 1: Obtain the Image File**

Determine the source of the image:
- **Local file**: User provides path to existing file on their system
- **URL**: User provides URL to download image from
- **Already in /images/**: User indicates image is already placed

**Step 2: Generate Descriptive Filename**

Create a kebab-case filename that describes the image:
- Include project name or content identifier
- Add descriptive suffix: `-screenshot`, `-logo`, `-diagram`, `-demo`, etc.
- Use appropriate file extension: `.png`, `.jpg`, `.gif`, `.svg`

Examples:
- `fairjams-screenshot.png`
- `gpassist-logo.jpg`
- `project-demo-2024.png`
- `anvil-workshop-photo.jpg`

**Validation checkpoint:** Filename matches `^[a-z0-9]+(-[a-z0-9]+)*\.(png|jpg|jpeg|gif|svg)$`

**Step 3: Process and Place Image File**

Based on the image source:

**For local files:**
```bash
# Copy to images directory with descriptive name
cp ~/path/to/image.png images/project-name-screenshot.png
```

**For URLs:**
```bash
# Download directly to images directory
curl -o images/project-name-screenshot.png https://example.com/image.png
```

**For large images (optional optimization):**
```bash
# Resize and optimize before placing
convert ~/path/to/large-image.png -resize 1200x -strip -quality 85 images/project-name-screenshot.png
```

**Validation checkpoint:** 
- Image file exists in `/images/` directory
- Filename follows kebab-case convention
- File extension is supported format

**Step 4: Generate Embed Code**

Create the HTML embed code based on usage context:

**For excerpt (thumbnail):**
```html
<br/><br/><img src='/images/project-name-screenshot.png' style='height: 300px'>
```

**For body content (standard):**
```html
<img src='/images/project-name-screenshot.png' style='height: 300px'>
```

**For custom dimensions:**
```html
<!-- Custom height -->
<img src='/images/wide-diagram.png' style='height: 500px'>

<!-- Custom width -->
<img src='/images/screenshot.png' style='width: 800px'>

<!-- Both dimensions -->
<img src='/images/banner.png' style='width: 1000px; height: 300px'>
```

**Step 5: Insert Embed Code**

Determine where to place the image:

**In excerpt field:**
- Add to frontmatter `excerpt` field
- Include `<br/><br/>` before image for spacing
- Example: `excerpt: "Description text<br/><br/><img src='/images/image.png' style='height: 300px'>"`

**In body content:**
- Insert at appropriate location in markdown body
- Add blank lines before and after for proper spacing
- Place near related text or in dedicated media section

**Step 6: Validate and Confirm**

- Verify image file is in `/images/` directory
- Verify embed code uses correct filename
- Verify style attribute has proper dimensions
- Confirm placement in content (excerpt or body)

**Complete example - Adding image to existing project:**

```
User: "Add a screenshot to the FairJams project"

Assistant actions:
1. Ask for image source: "Please provide the image file path or URL"
2. User provides: ~/Desktop/fairjams-app.png
3. Generate filename: fairjams-screenshot.png
4. Copy file: cp ~/Desktop/fairjams-app.png images/fairjams-screenshot.png
5. Generate embed: <img src='/images/fairjams-screenshot.png' style='height: 300px'>
6. Ask placement: "Add to excerpt or body content?"
7. User chooses: "body"
8. Read current content, insert embed after description
9. Write updated file
10. Confirm: "Added screenshot to FairJams project body"
```

#### Embedding YouTube Videos

Follow these steps to embed a YouTube video:

**Step 1: Obtain Video URL**

Ask user for the YouTube video URL. Accepted formats:
- `https://www.youtube.com/watch?v=VIDEO_ID`
- `https://youtu.be/VIDEO_ID`
- Just the video ID: `VIDEO_ID`

**Step 2: Extract Video ID**

Parse the URL to extract the video ID:
- From `watch?v=` format: ID is after `v=`
- From `youtu.be/` format: ID is after the domain
- If user provides just ID: use as-is

Examples:
- `https://www.youtube.com/watch?v=KNwLJif7N14` → `KNwLJif7N14`
- `https://youtu.be/KNwLJif7N14` → `KNwLJif7N14`

**Validation checkpoint:** Video ID should be alphanumeric string (typically 11 characters)

**Step 3: Generate Iframe Embed Code**

Use the standard YouTube embed template:

```html
<iframe width="768" height="543" src="https://www.youtube.com/embed/VIDEO_ID" 
        title="YouTube video player" frameborder="0" 
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
        allowfullscreen></iframe>
```

Replace `VIDEO_ID` with the extracted ID.

**Alternative dimensions (if user requests):**
- Standard large: `width="768" height="543"`
- Standard medium: `width="560" height="315"`
- Custom: maintain 16:9 aspect ratio

**Step 4: Insert Embed Code**

Place the iframe in the markdown body:
- Add blank line before iframe
- Add blank line after iframe
- Place near related content or in dedicated video section
- Optionally add descriptive text before video

**Step 5: Validate and Confirm**

- Verify video ID is correct
- Verify iframe has all required attributes
- Verify proper spacing around iframe
- Confirm placement in content

**Complete example - Adding video to project:**

```
User: "Add this video to GPAssist: https://www.youtube.com/watch?v=r70OTzcWVCM"

Assistant actions:
1. Extract video ID: r70OTzcWVCM
2. Generate iframe embed with ID
3. Read current GPAssist content
4. Insert iframe in body after description
5. Add blank lines for spacing
6. Write updated file
7. Confirm: "Added YouTube video to GPAssist project"
```

#### Using Custom Image Dimensions

When user requests specific image dimensions:

**Step 1: Clarify Dimension Requirements**

Ask user for:
- Specific height (e.g., "500px")
- Specific width (e.g., "800px")
- Both dimensions (e.g., "800px wide by 600px tall")
- Responsive sizing (e.g., "full width", "50% width")

**Step 2: Generate Custom Style Attribute**

Based on requirements:

**Height only (maintains aspect ratio):**
```html
<img src='/images/image.png' style='height: 500px'>
```

**Width only (maintains aspect ratio):**
```html
<img src='/images/image.png' style='width: 800px'>
```

**Both dimensions (may distort if ratio doesn't match):**
```html
<img src='/images/image.png' style='width: 800px; height: 600px'>
```

**Responsive percentage:**
```html
<img src='/images/image.png' style='width: 100%'>
<img src='/images/image.png' style='width: 50%'>
```

**Additional styling (if requested):**
```html
<!-- Centered -->
<img src='/images/image.png' style='height: 300px; display: block; margin: 0 auto'>

<!-- With border -->
<img src='/images/image.png' style='height: 300px; border: 2px solid #ccc'>

<!-- With shadow -->
<img src='/images/image.png' style='height: 300px; box-shadow: 0 4px 6px rgba(0,0,0,0.1)'>
```

**Step 3: Apply Custom Dimensions**

Replace the default `style='height: 300px'` with custom style attribute.

**Step 4: Validate**

- Verify style attribute syntax is correct
- Verify dimensions are reasonable (not too large or too small)
- Suggest adjustments if dimensions seem problematic

**Best practices for custom dimensions:**
- Prefer height-based sizing to maintain aspect ratio
- Use width-based sizing for very wide images (screenshots, diagrams)
- Avoid fixed both dimensions unless necessary (can distort image)
- Consider responsive sizing for better mobile experience
- Test appearance if possible

**Example - Custom dimensions:**

```
User: "Make the FairJams screenshot 500px tall"

Assistant actions:
1. Locate image embed in FairJams content
2. Change style from 'height: 300px' to 'height: 500px'
3. Update file
4. Confirm: "Updated FairJams screenshot to 500px height"
```

```
User: "Make the diagram full width"

Assistant actions:
1. Locate image embed
2. Change style to 'width: 100%'
3. Update file
4. Confirm: "Updated diagram to full width (100%)"
```

#### Media Workflow Best Practices

**Efficiency tips:**
- Use command-line tools (cp, curl, convert) for faster file operations
- Batch process multiple images when adding several at once
- Optimize large images before adding to reduce repository size
- Use descriptive filenames for easy identification later

**Organization tips:**
- Keep `/images/` directory organized with clear naming
- Use consistent naming patterns (project-name-type.ext)
- Document image sources if needed for attribution
- Remove unused images periodically to keep repository clean

**Quality tips:**
- Resize very large images (>2MB) before adding
- Use PNG for screenshots and graphics with transparency
- Use JPG for photographs and complex images
- Optimize images to balance quality and file size
- Test image appearance in the built site when possible



## Configuration Management

This section provides guidance on managing the Jekyll site configuration file (`_config.yml`), including updating author profile information, avatar images, and social media links while preserving YAML validity.

### Understanding _config.yml Structure

The `_config.yml` file is the central configuration file for the Jekyll site. It contains site-wide settings, author profile information, social media links, collection definitions, and Jekyll build configuration.

**File reference:** #[[file:_config.yml]]

**Key sections in _config.yml:**

1. **Site Settings** - Basic site metadata
   - `locale`, `title`, `name`, `description`
   - `url`, `baseurl`, `repository`
   - Various Jekyll build settings

2. **Author Section** - Profile information (this is what you'll update most often)
   - `name`, `avatar`, `bio`, `location`, `employer`
   - Social media links (GitHub, LinkedIn, Facebook, Instagram, YouTube, Discord, etc.)
   - Academic profile links (Google Scholar, ResearchGate, ORCID, etc.)

3. **Collections** - Defines content collections and their permalink structures
   - `teaching`, `publications`, `projects`, `musicart`, `talks`

4. **Jekyll Configuration** - Build settings, plugins, markdown processing
   - Plugins list, markdown settings, compression settings

**Important:** The `_config.yml` file uses YAML syntax. Any syntax errors will prevent Jekyll from building the site. Always validate YAML syntax after making changes.

### Updating Author Bio Information

The author bio information is stored in the `author` section of `_config.yml`. This section controls what appears in the author profile sidebar on the website.

#### Author Section Fields

**Core profile fields:**
- **`name`** - Your full name (appears in profile and site metadata)
- **`bio`** - Short biographical statement (appears under avatar in sidebar)
- **`location`** - Your location (city, state/country)
- **`employer`** - Current employer or affiliation (optional)

**Example author section:**
```yaml
author:
  name             : "Mihir Dhamankar"
  avatar           : "Mihir_profile.jpg"
  bio              : "Carnegie Mellon University B.S. CS '23, M.S. CS '24"
  location         : "Wayne, PA"
  employer         :
```

#### Workflow for Updating Bio Information

**Step 1: Read Current Configuration**

Before making any changes, read the entire `_config.yml` file:
- Parse the YAML to understand the current structure
- Locate the `author` section
- Identify the specific field(s) to update

**Step 2: Identify Fields to Update**

Determine which author fields the user wants to change:
- Name
- Bio
- Location
- Employer

**Step 3: Preserve Other Settings**

**CRITICAL:** When updating `_config.yml`, you must preserve all other configuration settings. Do not modify or remove any sections other than the specific fields being updated.

**Preservation rules:**
- Keep all site settings unchanged
- Keep all social media links unchanged (unless specifically updating those)
- Keep all Jekyll configuration unchanged
- Keep all collection definitions unchanged
- Maintain the exact YAML formatting and indentation

**Step 4: Apply Updates**

Modify only the specified author fields:
- Update the field value
- Maintain proper YAML syntax (use quotes for strings with special characters)
- Keep the same indentation (spaces, not tabs)
- Preserve the field alignment with other author fields

**Step 5: Validate YAML Syntax**

Before writing the file, validate that the YAML is still valid:
- Check for proper indentation (consistent spaces)
- Verify quotes are balanced
- Ensure colons have spaces after them
- Check for any special characters that need escaping

**Step 6: Write Updated Configuration**

Write the complete `_config.yml` file with the updated author fields.

**Step 7: Confirm Changes**

Inform the user of the successful update:
- Which fields were changed
- The new values
- Reminder that Jekyll needs to be restarted to see changes (if running locally)

**Example bio update:**

```
User: "Update my bio to: Software Engineer at Tech Company"

Assistant actions:
1. Read _config.yml
2. Locate author.bio field
3. Current value: "Carnegie Mellon University B.S. CS '23, M.S. CS '24"
4. Update to: "Software Engineer at Tech Company"
5. Preserve all other fields
6. Validate YAML syntax
7. Write updated file
8. Confirm: "Updated bio in _config.yml. Restart Jekyll to see changes."
```

**Example location update:**

```
User: "Change my location to San Francisco, CA"

Assistant actions:
1. Read _config.yml
2. Locate author.location field
3. Update from "Wayne, PA" to "San Francisco, CA"
4. Preserve all other fields
5. Write updated file
6. Confirm: "Updated location to San Francisco, CA"
```

### Updating Avatar Image

The avatar is the profile picture that appears in the author sidebar. Updating the avatar requires two steps: placing the image file and updating the configuration.

#### Avatar Update Workflow

**Step 1: Obtain Avatar Image**

Determine the source of the avatar image:
- Local file path
- URL to download from
- Already in `/images/` directory

**Step 2: Generate Avatar Filename**

Choose a descriptive filename for the avatar:
- Use your name or a generic identifier
- Common patterns: `firstname-profile.jpg`, `avatar.jpg`, `profile-photo.png`
- Use appropriate extension: `.jpg`, `.png`, `.gif`

Examples:
- `mihir-profile.jpg`
- `profile-photo.png`
- `avatar.jpg`

**Validation checkpoint:** Filename should be descriptive and use a supported image format.

**Step 3: Place Image in /images/ Directory**

Copy or download the avatar image to the `/images/` directory:

**For local files:**
```bash
cp ~/path/to/photo.jpg images/firstname-profile.jpg
```

**For URLs:**
```bash
curl -o images/firstname-profile.jpg https://example.com/photo.jpg
```

**Optional: Resize for optimal size**
```bash
# Resize to reasonable dimensions (e.g., 300x300 for avatar)
convert ~/path/to/photo.jpg -resize 300x300^ -gravity center -extent 300x300 images/firstname-profile.jpg
```

**Validation checkpoint:** Image file exists in `/images/` directory.

**Step 4: Update avatar Field in _config.yml**

Read `_config.yml` and update the `avatar` field in the `author` section:

**Current avatar field:**
```yaml
author:
  avatar           : "Mihir_profile.jpg"
```

**Updated avatar field:**
```yaml
author:
  avatar           : "firstname-profile.jpg"
```

**Important notes:**
- The avatar field should contain just the filename, not the full path
- Jekyll automatically looks for the avatar in the `/images/` directory
- Use quotes around the filename if it contains special characters

**Step 5: Preserve All Other Configuration**

When updating the avatar field, preserve all other author fields and all other configuration sections.

**Step 6: Validate YAML Syntax**

Ensure the updated configuration is still valid YAML.

**Step 7: Write Updated Configuration**

Write the complete `_config.yml` file with the updated avatar field.

**Step 8: Confirm Changes**

Inform the user of the successful update:
- Image file location: `/images/{filename}`
- Updated avatar field in `_config.yml`
- Reminder to restart Jekyll if running locally

**Complete avatar update example:**

```
User: "Update my avatar with this image: ~/Desktop/new-photo.jpg"

Assistant actions:
1. Generate filename: mihir-profile-new.jpg
2. Copy image: cp ~/Desktop/new-photo.jpg images/mihir-profile-new.jpg
3. Read _config.yml
4. Locate author.avatar field
5. Update from "Mihir_profile.jpg" to "mihir-profile-new.jpg"
6. Preserve all other fields
7. Validate YAML syntax
8. Write updated _config.yml
9. Confirm: "Avatar updated. Image saved to /images/mihir-profile-new.jpg and _config.yml updated. Restart Jekyll to see changes."
```

### Updating Social Media Links

Social media links are stored in the `author` section of `_config.yml`. Each social platform has its own field.

#### Available Social Media Fields

The Academic Pages theme supports these social media platforms:

**Common platforms:**
- `github` - GitHub username
- `linkedin` - LinkedIn username or profile ID
- `facebook` - Facebook username or profile ID
- `instagram` - Instagram username
- `twitter` - Twitter/X handle (without @)
- `youtube` - YouTube channel URL
- `discord` - Discord profile URL

**Academic platforms:**
- `googlescholar` - Google Scholar profile URL
- `researchgate` - ResearchGate profile URL
- `orcid` - ORCID profile URL
- `impactstory` - ImpactStory profile URL

**Other platforms:**
- `email` - Email address
- `uri` - Personal website URL
- `bitbucket` - Bitbucket username
- `stackoverflow` - Stack Overflow profile URL
- `codepen` - CodePen username
- `dribbble` - Dribbble username
- `flickr` - Flickr username
- `foursquare` - Foursquare username
- `keybase` - Keybase username
- `lastfm` - Last.fm username
- `pinterest` - Pinterest username
- `soundcloud` - SoundCloud username
- `steam` - Steam username
- `tumblr` - Tumblr username
- `vine` - Vine username
- `weibo` - Weibo username
- `xing` - Xing username
- `wikipedia` - Wikipedia username

**Example social media fields:**
```yaml
author:
  github           : "Mdkar"
  linkedin         : "mihirdhamankar"
  facebook         : "mihir.dhamankar.549"
  instagram        : "m_dkar"
  youtube          : "https://www.youtube.com/channel/UCRAChOG4nMoCrIzdJFT6bLQ"
  discord          : "https://discordapp.com/users/klausklass#5140/"
```

#### Social Link Field Formats

Different platforms use different formats for their links:

**Username only (most common):**
- GitHub, LinkedIn, Facebook, Instagram, Twitter
- Just provide the username without URL
- Example: `github: "username"`

**Full URL:**
- YouTube, Discord, personal websites
- Provide the complete URL
- Example: `youtube: "https://www.youtube.com/channel/CHANNEL_ID"`

**Empty/commented fields:**
- Fields can be left empty or commented out if not used
- Example: `twitter: #"replacethistwitterhandle"` (commented out)
- Example: `email:` (empty)

#### Workflow for Updating Social Media Links

**Step 1: Read Current Configuration**

Read `_config.yml` and locate the `author` section with social media fields.

**Step 2: Identify Platform to Update**

Determine which social media platform(s) the user wants to update:
- Adding a new link
- Updating an existing link
- Removing a link

**Step 3: Determine Correct Field Format**

Based on the platform, determine the correct format:
- Username only or full URL?
- Does the field already exist or need to be added?

**Step 4: Apply Update**

Modify the specific social media field:

**Adding a new link:**
- If field exists but is empty, add the value
- If field is commented out, uncomment and add value
- Maintain proper indentation and alignment

**Updating existing link:**
- Replace the current value with the new value
- Maintain the same format (username vs URL)

**Removing a link:**
- Set field to empty or comment it out
- Do not delete the field entirely (preserves structure)

**Step 5: Preserve All Other Configuration**

Keep all other author fields and configuration sections unchanged.

**Step 6: Validate YAML Syntax**

Ensure the updated configuration is valid YAML:
- Check quotes are balanced
- Verify indentation is correct
- Ensure special characters in URLs are handled properly

**Step 7: Write Updated Configuration**

Write the complete `_config.yml` file with updated social media fields.

**Step 8: Confirm Changes**

Inform the user of the successful update:
- Which platform was updated
- The new value
- Reminder to restart Jekyll if running locally

**Example social media updates:**

**Adding GitHub link:**
```
User: "Add my GitHub username: johndoe"

Assistant actions:
1. Read _config.yml
2. Locate author.github field
3. Current value: (empty or commented)
4. Update to: "johndoe"
5. Preserve all other fields
6. Write updated file
7. Confirm: "Added GitHub link: johndoe"
```

**Updating LinkedIn:**
```
User: "Update my LinkedIn to: john-doe-123"

Assistant actions:
1. Read _config.yml
2. Locate author.linkedin field
3. Update from current value to "john-doe-123"
4. Preserve all other fields
5. Write updated file
6. Confirm: "Updated LinkedIn to: john-doe-123"
```

**Adding YouTube channel:**
```
User: "Add my YouTube channel: https://www.youtube.com/channel/UC123456"

Assistant actions:
1. Read _config.yml
2. Locate author.youtube field
3. Update to: "https://www.youtube.com/channel/UC123456"
4. Preserve all other fields
5. Write updated file
6. Confirm: "Added YouTube channel link"
```

**Removing Twitter:**
```
User: "Remove my Twitter link"

Assistant actions:
1. Read _config.yml
2. Locate author.twitter field
3. Set to empty or comment out: twitter: #
4. Preserve all other fields
5. Write updated file
6. Confirm: "Removed Twitter link"
```

### YAML Validity Preservation

Maintaining valid YAML syntax is critical when updating `_config.yml`. Invalid YAML will prevent Jekyll from building the site.

#### YAML Syntax Rules

**Indentation:**
- Use spaces, not tabs
- Maintain consistent indentation (typically 2 spaces per level)
- Align fields at the same level

**Colons:**
- Always include a space after the colon: `key: value`
- No space before the colon

**Quotes:**
- Use quotes for strings containing special characters: `:`, `#`, `@`, `!`, `|`, `>`, `[`, `]`, `{`, `}`, `,`, `&`, `*`, `?`, `-`
- Use quotes for strings that look like numbers or booleans but should be strings
- Balance opening and closing quotes
- Can use single quotes `'` or double quotes `"`
- Escape quotes within quoted strings: `"He said \"hello\""`

**Comments:**
- Start with `#` character
- Can appear on their own line or at the end of a line
- Example: `# This is a comment`
- Example: `field: value  # inline comment`

**Lists:**
- Use hyphen followed by space: `- item`
- Maintain consistent indentation for list items

**Booleans:**
- Use `true` or `false` (lowercase, no quotes)
- Example: `enabled: true`

**Numbers:**
- No quotes needed for numeric values
- Example: `port: 4000`

**Empty values:**
- Can be represented as empty after colon: `field:`
- Or explicitly as null: `field: null`

#### Common YAML Errors to Avoid

**Incorrect indentation:**
```yaml
# Wrong
author:
name: "John"  # Not indented
  bio: "Developer"  # Inconsistent indentation

# Correct
author:
  name: "John"
  bio: "Developer"
```

**Missing space after colon:**
```yaml
# Wrong
name:"John"

# Correct
name: "John"
```

**Unquoted special characters:**
```yaml
# Wrong
bio: Carnegie Mellon: Computer Science  # Colon needs quotes

# Correct
bio: "Carnegie Mellon: Computer Science"
```

**Unbalanced quotes:**
```yaml
# Wrong
bio: "This is a quote

# Correct
bio: "This is a quote"
```

**Tab characters:**
```yaml
# Wrong (using tabs)
author:
	name: "John"

# Correct (using spaces)
author:
  name: "John"
```

#### Validation Workflow

When updating `_config.yml`, follow this validation workflow:

**Step 1: Read Original File**

Before making changes, read and parse the original `_config.yml`:
- Verify it's currently valid YAML
- Understand the structure
- Note the indentation style

**Step 2: Make Targeted Changes**

Apply only the specific changes requested:
- Modify only the target fields
- Maintain the same indentation style
- Preserve all other content exactly

**Step 3: Validate Updated YAML**

Before writing the file, validate the updated YAML:
- Parse the YAML to check for syntax errors
- Verify indentation is consistent
- Check quotes are balanced
- Ensure special characters are properly quoted

**Step 4: Handle Validation Errors**

If validation fails:
- Identify the specific syntax error
- Report the error to the user with the line number
- Suggest the correction
- Do not write the invalid file

**Step 5: Write Valid Configuration**

Only write the file if validation passes.

**Step 6: Recommend Testing**

After updating `_config.yml`, recommend that the user:
- Restart Jekyll if running locally (changes require restart)
- Test the site build: `bundle exec jekyll serve`
- Verify the changes appear correctly on the site

#### Example Validation Error Handling

```
User: "Update my bio to: I'm a developer @ Company"

Assistant actions:
1. Read _config.yml
2. Prepare update: bio: "I'm a developer @ Company"
3. Validate YAML: Valid (special characters are quoted)
4. Write updated file
5. Confirm: "Updated bio. Restart Jekyll to see changes."
```

```
User: "Update my bio to: Developer: Full Stack"

Assistant actions:
1. Read _config.yml
2. Prepare update: bio: "Developer: Full Stack"
3. Validate YAML: Valid (colon is quoted)
4. Write updated file
5. Confirm: "Updated bio. Restart Jekyll to see changes."
```

**Error example:**
```
User: "Update my bio to: Developer: Full Stack" (but assistant forgets quotes)

Assistant actions:
1. Read _config.yml
2. Prepare update: bio: Developer: Full Stack
3. Validate YAML: ERROR - Unquoted colon in string
4. Report error: "YAML syntax error: String contains colon and must be quoted"
5. Suggest correction: bio: "Developer: Full Stack"
6. Do not write file
```

### Configuration Update Best Practices

**Always read first:**
- Never assume the structure of `_config.yml`
- Always read the current file before making changes
- Understand what's already there

**Minimal changes:**
- Modify only what's requested
- Preserve everything else exactly
- Don't "clean up" or reorganize unless asked

**Validate before writing:**
- Always validate YAML syntax before writing
- Catch errors before they break the site
- Report errors clearly with suggestions

**Preserve formatting:**
- Maintain the same indentation style
- Keep the same quote style (single vs double)
- Preserve comments and empty lines

**Test recommendations:**
- Remind users to restart Jekyll after config changes
- Suggest testing the build locally
- Verify changes appear correctly on the site

**Backup suggestion:**
- For major config changes, suggest backing up the original file
- Git version control is ideal for tracking config changes

**Common update patterns:**
- Bio updates: Most frequent, usually straightforward
- Social links: Often adding new or updating existing
- Avatar: Requires both file placement and config update
- Name/location: Less frequent but straightforward

**Error recovery:**
- If a config update breaks the site, revert to the previous version
- Use git to restore: `git checkout _config.yml`
- Keep a backup of working configuration
