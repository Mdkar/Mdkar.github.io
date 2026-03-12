# Implementation Plan: Website Content Manager

## Overview

This implementation creates a comprehensive steering document at `.kiro/steering/jekyll-content-management.md` that teaches Kiro how to manage content on the Jekyll-based academic website. The steering document will include site structure documentation, content templates for all collection types, validation rules, workflow guides, and media embedding patterns.

## Tasks

- [x] 1. Create steering document foundation
  - Create `.kiro/steering/` directory if it doesn't exist
  - Create `jekyll-content-management.md` with document header and overview section
  - Document the Jekyll site structure (collections, directories, configuration)
  - Note which collections are used - most are unused and will not be used for the forseeable future. They only have dummy examples.
  - Add glossary of key terms
  - _Requirements: Introduction, Glossary_

- [x] 2. Document collection types and templates
  - [x] 2.1 Add projects collection documentation
    - Document required and optional frontmatter fields
    - Provide body content structure guidance
    - Add file reference to `_projects/FairJams.md` as example
    - Add file reference to `_projects/GPAssist.md` as example
    - _Requirements: 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8_

  - [x] 2.5 Add musicart collection documentation
    - Document required and optional frontmatter fields
    - Provide creative work structure guidance
    - Add file reference to `_musicart/anvil.md` as example
    - _Requirements: 7.1, 7.2, 7.3, 7.4_

- [x] 3. Add validation rules section
  - Document frontmatter validation requirements (triple dashes, YAML syntax, required fields)
  - Document permalink format validation (/{collection}/{slug}/ pattern)
  - Document date format validation (YYYY-M-D or YYYY-MM-DD)
  - Document filename validation (kebab-case, .md extension, uniqueness)
  - Document collection field consistency rules
  - _Requirements: 8.1, 8.2, 8.3, 8.4, 8.5, 8.6, 10.1, 10.2, 10.3, 10.4, 10.5_

- [x] 4. Add media asset handling section
  - Document image file placement in /images/ directory
  - Document image filename conventions (kebab-case, supported formats)
  - Provide image embedding template with style attribute
  - Provide YouTube video iframe embedding template
  - Document custom dimension handling
  - Add suggestion to use command line tools to get media to the right folder efficiently (use them to copy, download, resize, etc)
  - _Requirements: 3.1, 3.2, 3.3, 3.4, 3.5, 3.6_

- [-] 5. Create workflow guides
  - [x] 5.1 Add "Creating New Content" workflow
    - Step-by-step guide for adding new projects
    - Step-by-step guide for adding new musicart entries
    - Include validation checkpoints in each workflow
    - _Requirements: 1.1-1.8, 4.1-4.5, 5.1-5.4, 6.1-6.4, 7.1-7.4, 12.4_
  
  - [x] 5.2 Add "Updating Existing Content" workflow
    - Guide for locating content by title or permalink
    - Guide for updating frontmatter fields while preserving others
    - Guide for replacing vs appending body content
    - Guide for handling non-existent files
    - _Requirements: 2.1, 2.2, 2.3, 2.4, 12.1, 12.2, 12.3_
  
  - [x] 5.3 Add "Managing Media Assets" workflow
    - Guide for adding images to projects
    - Guide for embedding YouTube videos
    - Guide for using custom image dimensions
    - _Requirements: 3.1, 3.2, 3.3, 3.4, 3.5, 3.6_
  - [ ] 5.4 Do setup to test the UI locally
    - Follow the steps in README.md for "To run locally (not on GitHub Pages, to serve on your own computer)", do the setup steps now (once - no need to create a workflow for this)

- [x] 6. Add configuration management section
  - Document _config.yml structure with file reference
  - Guide for updating author bio information
  - Guide for updating avatar (file placement + config update)
  - Guide for updating social media links
  - Document YAML validity preservation requirements
  - _Requirements: 9.1, 9.2, 9.3, 9.4, 9.5_

- [ ] 7. Add error handling guidance
  - Document how to handle missing required information
  - Document how to report validation errors clearly
  - Document how to handle file system errors
  - Document how to handle file not found scenarios
  - Document how to report multiple issues at once
  - _Requirements: 12.1, 12.2, 12.3, 12.5_

- [ ] 8. Add formatting consistency guidelines
  - Document markdown formatting conventions (headers, lists, links, code blocks)
  - Document section separation with two newlines
  - Document image embed format consistency
  - Document video embed format consistency
  - Reference existing file patterns for consistency
  - _Requirements: 11.1, 11.2, 11.3, 11.4, 11.5_

- [ ] 9. Checkpoint - Review steering document completeness
  - Verify all collection types are documented
  - Verify all validation rules are clearly stated
  - Verify all workflows are actionable and complete
  - Verify all file references are valid
  - Ensure all tests pass, ask the user if questions arise.

- [ ] 10. Test steering document with real usage scenarios
  - Test adding a new project using the steering document, ask the user for input for the real project to be added
  - Test adding media assets using the steering document, ask user for a link to an image
  - Test updating profile information using the steering document
  - Verify generated files match Jekyll conventions
  - _Requirements: All requirements_

- [ ] 11. Final checkpoint - Validate and document
  - Ensure steering document is complete and accurate
  - Verify all file references point to existing files
  - Confirm all requirements are covered
  - Ensure all tests pass, ask the user if questions arise.

## Notes

- The steering document is the primary deliverable - it teaches Kiro how to manage Jekyll content
- File references use the format `#[[file:path/to/file.md]]` to link to examples
- Each workflow should be clear and actionable with specific steps
- Validation rules should be explicit and checkable
- The document should be comprehensive enough for any chat session to handle content management
- All requirements are covered through the steering document's guidance and templates

