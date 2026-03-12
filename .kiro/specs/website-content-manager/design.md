# Design Document: Website Content Manager

## Overview

The Website Content Manager is implemented as a steering document that teaches Kiro how to manage content on a Jekyll-based academic website. Rather than building a custom agent or power, this approach uses Kiro's steering file system to provide always-available guidance for content management tasks.

The steering document acts as a comprehensive reference guide that:
- Documents the Jekyll site structure and conventions
- Provides templates for each content type (projects, publications, teaching, talks, musicart)
- References existing files as examples
- Defines validation rules and formatting standards
- Guides Kiro through common content management workflows

This design enables any chat session to handle content updates without requiring special invocations or context setup.

## Architecture

### Steering File Location

The steering document will be placed at:
```
.kiro/steering/jekyll-content-management.md
```

This location makes it always available to Kiro during any chat session.

### Document Structure

The steering file is organized into these major sections:

1. **Site Overview** - High-level description of the Jekyll site structure
2. **Collection Definitions** - Details for each content type (projects, publications, etc.)
3. **Content Templates** - Frontmatter and body templates with examples
4. **File References** - Links to existing example files
5. **Validation Rules** - Requirements for frontmatter, permalinks, dates, filenames
6. **Common Workflows** - Step-by-step guides for adding/updating content
7. **Media Asset Handling** - Image and video embedding patterns
8. **Configuration Management** - How to update _config.yml safely

### Integration with Kiro

The steering document uses Kiro's file reference syntax to point to examples:
- `#[[file:_projects/FairJams.md]]` - Reference to example project file
- `#[[file:_config.yml]]` - Reference to site configuration

When Kiro encounters a content management request, it:
1. Reads the steering document for guidance
2. Follows the templates and patterns defined
3. References example files to understand formatting
4. Validates output against the rules
5. Creates or updates files accordingly

## Components and Interfaces

### Steering Document Components

#### 1. Site Structure Documentation

Describes the Jekyll site organization:
- Collection directories: `_projects/`, `_publications/`, `_teaching/`, `_talks/`, `_musicart/`
- Media directory: `/images/`
- Configuration file: `_config.yml`
- Theme: Academic Pages (Jekyll theme)

#### 2. Content Type Templates

For each collection type, provides:
- Required frontmatter fields
- Optional frontmatter fields
- Permalink format
- Date format
- Body content structure
- Example file reference

**Example template structure:**
```markdown
### Projects (_projects/)

**Required Frontmatter:**
- title: String
- collection: "projects"
- permalink: /projects/{kebab-case-title}/
- date: YYYY-M-D or YYYY-MM-DD

**Optional Frontmatter:**
- excerpt: String (can include HTML for images)

**Body Content:**
- Project description
- Awards/recognition
- Links to code/demos
- Embedded media (images, videos)
- Technical details

**Example:** #[[file:_projects/FairJams.md]]
```

#### 3. Validation Rules Section

Defines requirements that must be checked:

**Frontmatter Validation:**
- Enclosed in triple dashes (`---`)
- Valid YAML syntax
- Required fields present
- Collection field matches directory

**Permalink Validation:**
- Format: `/{collection}/{slug}/`
- Slug is kebab-case
- No special characters except hyphens

**Date Validation:**
- Format: `YYYY-M-D` or `YYYY-MM-DD`
- Valid date values

**Filename Validation:**
- Kebab-case format
- `.md` extension
- No special characters except hyphens
- Unique within collection

#### 4. Workflow Guides

Step-by-step instructions for common tasks:

**Adding a New Project:**
1. Gather required information (title, description, date)
2. Generate kebab-case filename from title
3. Check if filename exists, append number if needed
4. Create frontmatter using template
5. Format body content with description, links, media
6. Validate frontmatter and permalink
7. Write file to `_projects/{filename}.md`
8. Confirm creation with user

**Updating Existing Content:**
1. Locate file by title or permalink
2. Read current content
3. Parse frontmatter and body
4. Apply requested changes
5. Preserve unchanged fields
6. Validate updated content
7. Write updated file
8. Confirm update with user

**Adding Media Assets:**
1. Determine asset type (image, video)
2. For images: save to `/images/` with descriptive kebab-case name
3. For videos: use iframe embed code
4. Generate appropriate embed code
5. Insert into content body or excerpt
6. Confirm asset placement

#### 5. Media Embedding Patterns

**Image Embedding:**
```html
<!-- In excerpt (for thumbnails) -->
<img src='/images/{filename}' style='height: 300px'>

<!-- In body (full size or custom) -->
<img src='/images/{filename}' style='height: 300px'>
```

**Video Embedding (YouTube):**
```html
<iframe width="768" height="543" src="https://www.youtube.com/embed/{video-id}" 
        title="YouTube video player" frameborder="0" 
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" 
        allowfullscreen></iframe>
```

#### 6. Configuration Management

Guidelines for updating `_config.yml`:
- Always read entire file first
- Modify only the target fields
- Preserve all other settings
- Validate YAML syntax after changes
- Common updates: author bio, avatar, social links

### File Reference System

The steering document includes references to example files that Kiro can read for context:

**Project Examples:**
- `#[[file:_projects/FairJams.md]]` - Project with image and video
- `#[[file:_projects/GPAssist.md]]` - Project with awards and detailed instructions

**Publication Examples:**
- `#[[file:_publications/2009-10-01-paper-title-number-1.md]]` - Standard publication format

**Teaching Examples:**
- `#[[file:_teaching/2014-spring-teaching-1.md]]` - Teaching entry format

**Talks Examples:**
- `#[[file:_talks/2012-03-01-talk-1.md]]` - Talk entry format

**Music/Art Examples:**
- `#[[file:_musicart/anvil.md]]` - Music/art entry format

**Configuration:**
- `#[[file:_config.yml]]` - Site configuration structure

## Data Models

### Content File Structure

```yaml
---
title: String (required)
excerpt: String (optional, can contain HTML)
collection: String (required, must match directory)
permalink: String (required, format: /{collection}/{slug}/)
date: String (required, format: YYYY-M-D or YYYY-MM-DD)
# Additional fields vary by collection type
---

Markdown body content goes here...
```

### Collection-Specific Fields

**Publications:**
- `venue`: String (journal or conference name)
- `paperurl`: String (URL to paper PDF)
- `citation`: String (formatted citation)

**Teaching:**
- `type`: String (e.g., "Undergraduate course")
- `venue`: String (institution and department)
- `location`: String (city, country)

**Talks:**
- `type`: String (e.g., "Talk", "Conference presentation")
- `venue`: String (location and organization)
- `location`: String (city, state/country)

### Filename Format

Pattern: `{kebab-case-title}.md`

Examples:
- `fairjams.md`
- `gpassist.md`
- `metal-backend-implementation.md`

If filename exists, append number:
- `project-name-2.md`
- `project-name-3.md`

### Permalink Format

Pattern: `/{collection}/{kebab-case-slug}/`

Examples:
- `/projects/fairjams/`
- `/publications/paper-title-number-1/`
- `/teaching/2014-spring-teaching-1/`

### Date Format

Accepted formats:
- `YYYY-M-D` (e.g., `2021-10-2`, `2019-4-12`)
- `YYYY-MM-DD` (e.g., `2021-10-02`, `2019-04-12`)

Both formats are valid in the existing codebase.


## Correctness Properties

*A property is a characteristic or behavior that should hold true across all valid executions of a system—essentially, a formal statement about what the system should do. Properties serve as the bridge between human-readable specifications and machine-verifiable correctness guarantees.*

Before defining the correctness properties, I need to analyze which acceptance criteria from the requirements are testable as properties.


### Property 1: Collection Directory Placement

*For any* content type (projects, publications, teaching, talks, musicart), when creating content of that type, the generated markdown file should be placed in the corresponding collection directory (`_projects/`, `_publications/`, `_teaching/`, `_talks/`, `_musicart/`).

**Validates: Requirements 1.1, 4.1, 5.1, 6.1, 7.1**

### Property 2: Required Frontmatter Fields

*For any* content file created, the frontmatter should contain all required fields: `title`, `collection`, `permalink`, and `date`. The `collection` field value should match the collection type.

**Validates: Requirements 1.2, 4.2, 5.2, 6.2, 7.2, 8.6**

### Property 3: Permalink Format Consistency

*For any* content file created, the permalink should follow the format `/{collection}/{kebab-case-slug}/` where the collection matches the content type and the slug is derived from the title in kebab-case format.

**Validates: Requirements 1.3, 4.3, 5.3, 6.3, 7.3, 8.3**

### Property 4: Date Format Validation

*For any* content file created, the date field should match one of the valid formats: `YYYY-M-D` or `YYYY-MM-DD`, and represent a valid calendar date.

**Validates: Requirements 1.4, 8.4**

### Property 5: Optional Field Inclusion

*For any* content creation where an optional field (such as excerpt) is provided, that field should appear in the generated frontmatter with the provided value.

**Validates: Requirements 1.5**

### Property 6: Description Body Placement

*For any* content file created with a description, the description should appear in the markdown body section (after the closing `---` of frontmatter), not within the frontmatter itself.

**Validates: Requirements 1.6**

### Property 7: Markdown Link Formatting

*For any* URLs provided for inclusion in content (code links, paper links, course materials, talk recordings), they should be formatted as proper markdown links in the body: `[link text](url)`.

**Validates: Requirements 1.7, 4.5, 5.4, 6.4**

### Property 8: Additional Content Body Placement

*For any* additional information provided (tech stack, citation information, course details), it should be included in the markdown body section, not in frontmatter.

**Validates: Requirements 1.8, 4.4**

### Property 9: Content Lookup by Identifier

*For any* existing content file, the system should be able to locate it when given either its title or its permalink as an identifier.

**Validates: Requirements 2.1**

### Property 10: Frontmatter Update Preservation

*For any* frontmatter update operation on an existing file, all frontmatter fields not explicitly being updated should retain their original values.

**Validates: Requirements 2.2, 9.2**

### Property 11: Body Update Mode Respect

*For any* body content update operation, if the mode is "replace", the new content should replace the old body entirely; if the mode is "append", the new content should be added after the existing body.

**Validates: Requirements 2.3**

### Property 12: Image File Placement

*For any* image asset added to the site, the image file should be saved to the `/images/` directory.

**Validates: Requirements 3.1, 7.4**

### Property 13: Image Filename Kebab-Case

*For any* image filename generated or processed, it should be in kebab-case format (lowercase with hyphens separating words, no special characters except hyphens and the file extension).

**Validates: Requirements 3.2**

### Property 14: Image Embed Format

*For any* image embedded in content (body or excerpt), the HTML should follow the format: `<img src='/images/{filename}' style='height: {height}px'>` where height defaults to 300 if not specified.

**Validates: Requirements 3.3**

### Property 15: Video Embed Format

*For any* YouTube video embedded in content, the HTML should use an iframe element with `width="768"` and `height="543"` attributes, and the src should be `https://www.youtube.com/embed/{video-id}`.

**Validates: Requirements 3.4**

### Property 16: Custom Dimension Override

*For any* image embed where custom dimensions are provided, the style attribute should use the custom dimensions instead of the default 300px height.

**Validates: Requirements 3.5**

### Property 17: Image Format Support

*For any* image file with extension jpg, jpeg, png, gif, or svg, the system should accept it as a valid image format. Files with other extensions should be rejected or flagged.

**Validates: Requirements 3.6**

### Property 18: Frontmatter Delimiter Enclosure

*For any* content file generated, the frontmatter section should be enclosed by triple dashes (`---`) on separate lines at the beginning and end of the frontmatter block.

**Validates: Requirements 8.1**

### Property 19: Collection Field Directory Consistency

*For any* content file in a collection directory, the `collection` field in its frontmatter should match the directory name (e.g., files in `_projects/` should have `collection: projects`).

**Validates: Requirements 8.2**

### Property 20: Config Section Targeting

*For any* profile information update (bio, avatar, social links), the modifications should be applied to the `author` section of `_config.yml`, not to other configuration sections.

**Validates: Requirements 9.1**

### Property 21: Avatar Update Dual Operation

*For any* avatar update operation, both the image file should be placed in `/images/` AND the `avatar` field in the author section of `_config.yml` should be updated to reference the new image filename.

**Validates: Requirements 9.3**

### Property 22: Social Link Field Mapping

*For any* social media link update, the link should be placed in the corresponding field within the author section (e.g., GitHub link goes to `github` field, LinkedIn to `linkedin` field).

**Validates: Requirements 9.4**

### Property 23: Config YAML Validity Preservation

*For any* update to `_config.yml`, the resulting file should remain valid YAML (parseable without syntax errors).

**Validates: Requirements 9.5**

### Property 24: Filename Kebab-Case Generation

*For any* content title provided, the generated filename should be in kebab-case format: lowercase, spaces converted to hyphens, special characters removed (except hyphens), with `.md` extension appended.

**Validates: Requirements 10.1, 10.2, 10.4, 10.5**

### Property 25: Filename Collision Handling

*For any* filename that already exists in the target collection directory, the system should generate a unique filename by appending a numeric suffix (e.g., `-2`, `-3`) before the `.md` extension.

**Validates: Requirements 10.3**

### Property 26: Markdown Syntax Validity

*For any* content file generated, the markdown body should be syntactically valid (headers, lists, links, code blocks should follow proper markdown syntax).

**Validates: Requirements 11.4**

### Property 27: Section Separation Formatting

*For any* content body with multiple distinct sections, the sections should be separated by two newline characters (blank line between sections).

**Validates: Requirements 11.5**


## Error Handling

### Validation Errors

**Missing Required Information:**
- When required fields (title, date, description) are not provided, prompt the user for the missing information
- Provide clear indication of which fields are required
- Do not proceed with file creation until all required information is available

**Invalid Format Errors:**
- When dates don't match valid formats, report the invalid date and show expected formats
- When filenames contain invalid characters, show which characters are problematic
- When YAML syntax is invalid, report the specific syntax error location

**File System Errors:**
- When a file cannot be written (permissions, disk space), report the specific system error
- When a directory doesn't exist, report which directory is missing
- When a file cannot be read during updates, report the access error

### Content Errors

**File Not Found:**
- When updating content that doesn't exist, inform the user the file wasn't found
- Offer to create a new file with the provided information
- Show similar filenames if available to help user identify the correct file

**Duplicate Detection:**
- When a filename collision is detected, automatically append numeric suffix
- Inform user of the modified filename
- Ensure the generated filename is unique before writing

**Invalid References:**
- When image files referenced in embeds don't exist, warn the user
- When URLs are malformed, report the invalid URL
- Suggest corrections when possible

### Configuration Errors

**YAML Syntax Errors:**
- When `_config.yml` updates would create invalid YAML, reject the update
- Show the specific syntax error that would be introduced
- Preserve the original file unchanged

**Missing Configuration Sections:**
- When the author section doesn't exist in `_config.yml`, report the structural issue
- Do not attempt to create missing sections automatically
- Guide user to fix the configuration structure

### Recovery Strategies

**Graceful Degradation:**
- If optional features fail (e.g., image optimization), proceed with basic functionality
- Log warnings for non-critical failures
- Complete the primary operation successfully

**Rollback on Failure:**
- If a multi-step operation fails partway through, do not leave partial changes
- For config updates, read the original file first and restore it if validation fails
- For file creation, remove partially written files if the operation cannot complete

**User Confirmation:**
- Before overwriting existing files, confirm with the user
- Show a diff of changes when updating existing content
- Allow user to cancel operations before they're committed

## Testing Strategy

### Overview

The Website Content Manager will be tested using a dual approach:
1. **Unit tests** for specific examples, edge cases, and error conditions
2. **Property-based tests** for universal properties across all inputs

This combination ensures both concrete correctness (unit tests) and comprehensive coverage (property tests).

### Unit Testing

Unit tests will focus on:

**Specific Examples:**
- Creating a project with all fields populated (like FairJams example)
- Creating a publication with citation and paper URL
- Creating a teaching entry with course details
- Updating an existing project's description
- Adding an image to a project excerpt

**Edge Cases:**
- Empty or whitespace-only titles
- Titles with special characters (emoji, punctuation, unicode)
- Very long titles (>200 characters)
- Dates at boundary conditions (leap years, month boundaries)
- Filenames that would collide with existing files
- Missing optional fields
- Empty body content

**Error Conditions:**
- Missing required fields (title, date)
- Invalid date formats
- Invalid YAML in config updates
- Non-existent files during updates
- Unsupported image formats
- Malformed URLs

**Integration Points:**
- Reading and parsing existing Jekyll files
- Writing files that Jekyll can successfully build
- Updating _config.yml without breaking Jekyll
- Embedding media that renders correctly in Jekyll

### Property-Based Testing

Property-based tests will verify the 27 correctness properties defined above. Each property test will:
- Run a minimum of 100 iterations with randomized inputs
- Generate diverse test cases (various titles, dates, content types, etc.)
- Reference the design document property in a comment tag

**Property Test Framework:**
Since this is a steering document (not executable code), the property tests will be implemented when the actual tooling is built. The recommended approach:
- Use a property-based testing library appropriate for the implementation language
- For Python: use Hypothesis
- For JavaScript/TypeScript: use fast-check
- For other languages: use the standard PBT library for that ecosystem

**Test Tag Format:**
Each property test should include a comment:
```
# Feature: website-content-manager, Property 1: Collection Directory Placement
```

**Generator Strategies:**

For effective property-based testing, generators should produce:

*Title Generator:*
- Random words (1-10 words)
- Various cases (lowercase, uppercase, mixed, title case)
- Special characters (hyphens, apostrophes, numbers, unicode)
- Edge cases (empty, very long, all whitespace)

*Date Generator:*
- Valid dates across wide range (1990-2030)
- Both accepted formats (YYYY-M-D and YYYY-MM-DD)
- Edge cases (leap years, month boundaries, year boundaries)

*Content Generator:*
- Markdown with various elements (headers, lists, links, code blocks)
- Plain text
- Mixed HTML and markdown
- Empty content
- Very long content (>10,000 characters)

*Collection Type Generator:*
- All five collection types: projects, publications, teaching, talks, musicart
- Ensure even distribution across types

*URL Generator:*
- Valid HTTP/HTTPS URLs
- URLs with various path structures
- URLs with query parameters
- YouTube video URLs
- Malformed URLs (for error testing)

*Image Filename Generator:*
- Various extensions (jpg, jpeg, png, gif, svg)
- Different naming patterns (kebab-case, camelCase, spaces, special chars)
- Edge cases (very long names, unicode characters)

### Test Coverage Goals

**Unit Test Coverage:**
- All error handling paths exercised
- All content types (5 collections) tested
- All media embedding patterns tested
- Config update scenarios tested
- File collision handling tested

**Property Test Coverage:**
- All 27 correctness properties verified
- Minimum 100 iterations per property
- Diverse input generation for comprehensive coverage
- Edge cases naturally discovered through randomization

### Validation Testing

**Jekyll Build Validation:**
After generating content files, validate that:
- Jekyll can successfully parse the files
- Jekyll build completes without errors
- Generated pages are accessible at their permalinks
- Media embeds render correctly in the built site

**Manual Review:**
Periodically review generated content for:
- Professional appearance and formatting
- Consistency with existing content style
- Proper rendering in web browsers
- Accessibility compliance (alt text, semantic HTML)

### Regression Testing

**Baseline Examples:**
Maintain a set of known-good examples:
- One file from each collection type
- Files with various media embeds
- Files with complex markdown formatting
- The current _config.yml

**Change Detection:**
When updating the steering document or implementation:
- Re-run all tests to ensure no regressions
- Compare generated output against baseline examples
- Verify that existing content patterns are still supported

### Testing the Steering Document

Since the Website Content Manager is implemented as a steering document rather than executable code, testing involves:

**Document Validation:**
- Ensure all file references are valid and point to existing files
- Verify all templates include required fields
- Check that validation rules are clearly stated
- Confirm workflow guides are complete and actionable

**Practical Testing:**
- Use the steering document in actual Kiro sessions
- Test common workflows (add project, update publication, etc.)
- Verify that Kiro can follow the guidance successfully
- Collect feedback on clarity and completeness

**Iterative Improvement:**
- Update the steering document based on real usage
- Add examples for patterns that cause confusion
- Clarify validation rules that are misinterpreted
- Expand workflow guides for complex scenarios

