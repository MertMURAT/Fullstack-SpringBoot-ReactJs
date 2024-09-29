"use client"
import { Template, ImageCard } from "@/components";
import { Image } from "@/resources/image/image.resources";
import { useImageService } from "@/resources/image/image.service";
import { useState } from "react";

export default function GalleryPages() {
    const userService = useImageService();
    const [images, setImages] = useState<Image[]>([]);
    const [query, setQuery] = useState<string>('');
    const [extension, setExtension] = useState<string>('');

    async function searchImages() {
        console.log("Value digits : ", query);
        const result = await userService.search(query, extension);
        setImages(result);
        console.table(result);
        console.table("IMAGES : " + images);
    }

    function renderImageCard(image: Image) {
        return (
            <div key={image.id}>
                <ImageCard
                    src={image.url}
                    name={image.name}
                    size={image.size}
                    extension={image.extension}
                    dataUpload={image.uploadDate}
                />
            </div>
        );
    }

    function renderImageCards() {
        // return images.map(image => renderImageCard(image));
        return images.map(renderImageCard);
    }

    return (
        <Template>
            <section className="flex flex-col items-center justify-center my-5">
                <div className="flex space-x-4">
                    <input
                        onChange={event => setQuery(event.target.value)}
                        type="text" className="border px-3 py-2 rounded-lg text-gray-900" />
                    <select
                        onChange={event => setExtension(event.target.value)}
                        className="border px-4 py-2 rounded-lg text-gray-900">
                        <option value="">All formats</option>
                        <option value="PNG">PNG</option>
                        <option value="JPEG">JPEG</option>
                        <option value="GIF">GIF</option>
                    </select>
                    <button
                        onClick={searchImages}
                        className="bg-blue-500 text-white px-4 py-2 rounded-lg">Search</button>
                    <button className="bg-yellow-500 text-white px-4 py-2 rounded-lg">Add New</button>
                </div>
            </section>
            <h4 className="font-bold text-3xl py-2">Gallery</h4>
            <section className="grid grid-cols-4 gap-8">
                {
                    renderImageCards()
                }


            </section>
        </Template>
    );
}